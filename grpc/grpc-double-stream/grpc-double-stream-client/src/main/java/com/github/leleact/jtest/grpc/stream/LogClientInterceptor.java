package com.github.leleact.jtest.grpc.stream;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * log interceptor
 *
 * @author leleact
 * @since 2024-01-20
 */
@Slf4j
public class LogClientInterceptor implements ClientInterceptor {
    public static final Metadata.Key<String> TRACE_ID_KEY = Metadata.Key.of("traceId",
        Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new BackendForwardingClientCall<>(method,
            next.newCall(method, callOptions/*.withDeadlineAfter(10000, TimeUnit.MILLISECONDS)*/)) {

            @Override
            public void sendMessage(ReqT message) {
                log.info("Method: {}, Message: {}", methodName, message);
                super.sendMessage(message);
            }

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(TRACE_ID_KEY, UUID.randomUUID().toString());

                BackendListener<RespT> backendListener = new BackendListener<>(methodName, responseListener);
                super.start(backendListener, headers);
            }
        };
    }

    private static class BackendListener<ReqT> extends ClientCall.Listener<ReqT> {

        String methodName;
        ClientCall.Listener<ReqT> responseListener;

        protected BackendListener(String methodName, ClientCall.Listener<ReqT> responseListener) {
            super();
            this.methodName = methodName;
            this.responseListener = responseListener;
        }

        @Override
        public void onMessage(ReqT message) {
            log.info("Method: {}, Response: {}", methodName, message);
            responseListener.onMessage(message);
        }

        @Override
        public void onHeaders(Metadata headers) {
            responseListener.onHeaders(headers);
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            responseListener.onClose(status, trailers);
        }

        @Override
        public void onReady() {
            responseListener.onReady();
        }
    }

    private static class BackendForwardingClientCall<ReqT, RespT> extends io.grpc.ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {

        String methodName;

        protected BackendForwardingClientCall(MethodDescriptor<ReqT, RespT> method, ClientCall<ReqT, RespT> delegate) {
            super(delegate);
            methodName = method.getFullMethodName();
        }
    }
}

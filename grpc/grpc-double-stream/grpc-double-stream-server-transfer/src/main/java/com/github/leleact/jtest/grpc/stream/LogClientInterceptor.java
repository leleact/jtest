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
            public void start(Listener<RespT> responseListener, Metadata headers) {
                log.info("Stage: {} begin, Method: {}, headers: {}", "start", method.getFullMethodName(), headers);
                headers.put(TRACE_ID_KEY, UUID.randomUUID().toString());
                BackendListener<RespT> backendListener = new BackendListener<>(methodName, responseListener);
                super.start(backendListener, headers);
                log.info("Stage: {} end, Method: {}, headers: {}", "start", method.getFullMethodName(), headers);
            }

            @Override
            public void sendMessage(ReqT message) {
                log.info("Stage: {} begin, Method: {}, message: {}", "sendMessage", method.getFullMethodName(),
                    message);
                super.sendMessage(message);
                log.info("Stage: {} end, Method: {}, message: {}", "sendMessage", method.getFullMethodName(), message);
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
        public void onHeaders(Metadata headers) {
            log.info("Stage: {} begin, Method: {}, headers: {}", "onHeaders", methodName, headers);
            // TODO 对一个长连接,多次发送header,是追加还是替换
            responseListener.onHeaders(headers);
            log.info("Stage: {} begin, Method: {}, headers: {}", "onHeaders", methodName, headers);
        }

        @Override
        public void onMessage(ReqT message) {
            log.info("Stage: {} begin, Method: {}, message: {}", "onMessage", methodName, message);
            responseListener.onMessage(message);
            log.info("Stage: {} end, Method: {}, message: {}", "onMessage", methodName, message);
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            log.info("Stage: {} begin, Method: {}, status: {} trailers: {}", "onClose", methodName, status, trailers);
            responseListener.onClose(status, trailers);
            log.info("Stage: {} end, Method: {}, status: {},trailers: {}", "onClose", methodName, status, trailers);
        }

        @Override
        public void onReady() {
            log.info("Stage: {} begin, Method: {}", "onReady", methodName);
            responseListener.onReady();
            log.info("Stage: {} end, Method: {}", "onReady", methodName);
        }
    }

    private static class BackendForwardingClientCall<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {

        String methodName;

        protected BackendForwardingClientCall(MethodDescriptor<ReqT, RespT> method, ClientCall<ReqT, RespT> delegate) {
            super(delegate);
            methodName = method.getFullMethodName();
        }
    }
}

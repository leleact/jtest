package com.github.leleact.jtest.grpc.stream;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * log interceptor
 *
 * @author leleact
 * @since 2024-01-20
 */
@Slf4j
public class LogServerInterceptor implements ServerInterceptor {
    public static final Metadata.Key<String> TRACE_ID_KEY = Metadata.Key.of("traceId",
        Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        String traceId = headers.get(TRACE_ID_KEY);
        MDC.put("traceId", traceId);

        GrpcServerCall<ReqT, RespT> grpcServerCall = new GrpcServerCall<>(call);

        ServerCall.Listener<ReqT> listener = next.startCall(grpcServerCall, headers);

        return new GrpcForwardingServerCallListener<>(call.getMethodDescriptor(), listener) {
            @Override
            public void onReady() {
                log.info("Stage: {} begin, Method: {}", "onReady", methodName);
                super.onReady();
                log.info("Stage: {} end, Method: {}", "onReady", methodName);
            }

            @Override
            public void onMessage(ReqT message) {
                // receive message from client
                log.info("Stage: {} begin, Method: {}, Message: {}", "onMessage", methodName, message);
                super.onMessage(message);
                log.info("Stage: {} end, Method: {}, Message: {}", "onMessage", methodName, message);
            }

            @Override
            public void onHalfClose() {
                log.info("Stage: {} begin, Method: {}", "onHalfClose", methodName);
                super.onHalfClose();
                log.info("Stage: {} end, Method: {}", "onHalfClose", methodName);
            }

            @Override
            public void onComplete() {
                log.info("Stage: {} begin, Method: {}", "onComplete", methodName);
                super.onComplete();
                log.info("Stage: {} end, Method: {}", "onComplete", methodName);
                MDC.clear();
            }

            @Override
            public void onCancel() {
                log.info("Stage: {} begin, Method: {}", "onCancel", methodName);
                super.onCancel();
                log.info("Stage: {} end, Method: {}", "onCancel", methodName);
                MDC.clear();
            }
        };
    }

    private static class GrpcServerCall<M, R> extends ServerCall<M, R> {

        ServerCall<M, R> serverCall;

        protected GrpcServerCall(ServerCall<M, R> serverCall) {
            this.serverCall = serverCall;
        }

        @Override
        public void request(int numMessages) {
            serverCall.request(numMessages);
        }

        @Override
        public void sendHeaders(Metadata headers) {
            serverCall.sendHeaders(headers);
        }

        @Override
        public void sendMessage(R message) {
            log.info("State: {} begin, Method: {}, Response: {}", "sendMessage",
                serverCall.getMethodDescriptor().getFullMethodName(), message);
            serverCall.sendMessage(message);
            log.info("State: {} end, Method: {}, Response: {}", "sendMessage",
                serverCall.getMethodDescriptor().getFullMethodName(), message);
        }

        @Override
        public void close(Status status, Metadata trailers) {
            serverCall.close(status, trailers);
        }

        @Override
        public boolean isCancelled() {
            return serverCall.isCancelled();
        }

        @Override
        public MethodDescriptor<M, R> getMethodDescriptor() {
            return serverCall.getMethodDescriptor();
        }
    }

    private static class GrpcForwardingServerCallListener<ReqT, RespT> extends io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {

        String methodName;

        protected GrpcForwardingServerCallListener(MethodDescriptor<ReqT, RespT> method, ServerCall.Listener<ReqT> listener) {
            super(listener);
            methodName = method.getFullMethodName();
        }
    }
}

package com.github.leleact.jtest.grpc.stream;

import com.github.leleact.jtest.grpc.api.GrpcStreamServiceGrpc;
import com.github.leleact.jtest.grpc.api.RequestMessage;
import com.github.leleact.jtest.grpc.api.ResponseMessage;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * double stream service
 *
 * @author leleact
 * @since 2023-10-12
 */
@Slf4j
public class DoubleStreamService extends GrpcStreamServiceGrpc.GrpcStreamServiceImplBase {

    @SneakyThrows
    public static void main(String[] args) {
        int serverPort = 10881;
        Server server = ServerBuilder.forPort(serverPort)
                                     .intercept(new LogServerInterceptor())
                                     .addService(new DoubleStreamService())
                                     .build();
        server.start();
        log.info("OrderServerBoot started, listening on:" + serverPort);
        server.awaitTermination();
    }

    @Override
    public StreamObserver<RequestMessage> bidirectionalStreamRpc(StreamObserver<ResponseMessage> responseObserver) {
        log.info("[DoubleStreamService] connected: {}", responseObserver);
        return new StreamObserver<>() {
            @Override
            public void onNext(RequestMessage request) {
                String msg = request.getReqMsg();
                String respMsg = "[RESP]" + msg;
                log.info("[DoubleStreamService] Client MSG: {}, RESP MSG: {}", msg, respMsg);
                responseObserver.onNext(ResponseMessage.newBuilder().setRspMsg(respMsg).build());
                responseObserver.onNext(ResponseMessage.newBuilder().setRspMsg(respMsg + 1).build());
            }

            @Override
            public void onError(Throwable t) {
                log.warn("[DoubleStreamService] gRPC dealing error", t);
            }

            @Override
            public void onCompleted() {
                log.info("[DoubleStreamService] completed");
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void unaryRpc(RequestMessage request, StreamObserver<ResponseMessage> responseObserver) {
        log.info("[DoubleStreamService] unaryRpc: {}", responseObserver);
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        responseObserver.onNext(ResponseMessage.newBuilder().setRspMsg(request.getReqMsg() + 1).build());
        responseObserver.onCompleted();
    }
}

package com.github.leleact.jtest.grpc.stream;

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
public class DoubleStreamService extends DoubleStreamServiceGrpc.DoubleStreamServiceImplBase {

    @SneakyThrows
    public static void main(String[] args) {
        int serverPort = 10881;
        Server server = ServerBuilder.forPort(serverPort).addService(new DoubleStreamService()).build();
        server.start();
        log.info("OrderServerBoot started, listening on:" + serverPort);
        server.awaitTermination();
    }

    @Override
    public StreamObserver<RequestMessage> doubleWayStreamFun(StreamObserver<ResponseMessage> responseObserver) {
        log.info("[DoubleStreamService] connected: {}", responseObserver);
        return new StreamObserver<RequestMessage>() {
            @Override
            public void onNext(RequestMessage request) {
                String msg = request.getReqMsg();
                String respMsg = "[RESP]" + msg;
                log.info("[DoubleStreamService] Client MSG: {}, RESP MSG: {}", msg, respMsg);
                responseObserver.onNext(ResponseMessage.newBuilder().setRspMsg(respMsg).build());
            }

            @Override
            public void onError(Throwable throwable) {
                log.warn("[DoubleStreamService] gRPC dealing error");
            }

            @Override
            public void onCompleted() {
                log.info("[DoubleStreamService] completed");
                responseObserver.onCompleted();
            }
        };
    }
}

package com.github.leleact.jtest.grpc.stream;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * double stream service
 *
 * @author leleact
 * @since 2023-10-12
 */
@Slf4j
public class DoubleStreamService extends DoubleStreamServiceGrpc.DoubleStreamServiceImplBase {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @SneakyThrows
    public static void main(String[] args) {
        startGrpcServer();
        countDownLatch.await();
    }

    @Override
    public StreamObserver<RequestMessage> doubleWayStreamFun(StreamObserver<ResponseMessage> responseObserver) {
        return new StreamObserver<RequestMessage>() {
            @Override
            public void onNext(RequestMessage chatRequest) {
                String msg = chatRequest.getReqMsg();
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
                responseObserver.onCompleted();
            }
        };
    }

    @SneakyThrows
    private static void startGrpcServer() {
        int serverPort = 10881;
        Server server = ServerBuilder.forPort(serverPort).addService(new DoubleStreamService()).build();
        server.start();
        log.info("OrderServerBoot started, listening on:" + serverPort);
    }
}

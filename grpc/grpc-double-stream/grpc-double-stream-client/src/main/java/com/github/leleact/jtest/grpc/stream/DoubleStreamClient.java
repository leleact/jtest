package com.github.leleact.jtest.grpc.stream;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * double stream client
 *
 * @author leleact
 * @since 2023-10-12
 */
@Slf4j
public class DoubleStreamClient {
    public static void main(String[] args) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 10881).usePlaintext();
        ManagedChannel channel = channelBuilder.build();
        StreamObserver<ResponseMessage> chatResponseStreamObserver = new StreamObserver<ResponseMessage>() {

            @Override
            public void onNext(ResponseMessage chatResponse) {
                log.info("[DoubleStreamClient] onNext...MSG: {}", chatResponse.getRspMsg());
            }

            @Override
            public void onError(Throwable throwable) {
                log.warn("[DoubleStreamClient] gRPC request error");
            }

            @Override
            public void onCompleted() {
                log.info("[DoubleStreamClient] onCompleted");
            }
        };

        DoubleStreamServiceGrpc.DoubleStreamServiceStub doubleStreamServiceStub = DoubleStreamServiceGrpc.newStub(
            channel);
        StreamObserver<RequestMessage> chatRequestStreamObserver = doubleStreamServiceStub.doubleWayStreamFun(
            chatResponseStreamObserver);

        Scanner scanner = new Scanner(System.in);
        for (; true; ) {
            String str = scanner.nextLine();
            if (str.equals("EOF")) {
                chatRequestStreamObserver.onCompleted();
                break;
            }
            try {
                chatRequestStreamObserver.onNext(RequestMessage.newBuilder().setReqMsg(scanner.nextLine()).build());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}

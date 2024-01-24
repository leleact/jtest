package com.github.leleact.jtest.grpc.stream;

import com.github.leleact.jtest.grpc.api.GrpcStreamServiceGrpc;
import com.github.leleact.jtest.grpc.api.RequestMessage;
import com.github.leleact.jtest.grpc.api.ResponseMessage;
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
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 10881)
                                                                       .usePlaintext()
                                                                       .intercept(new LogClientInterceptor());
        ManagedChannel channel = channelBuilder.build();
        StreamObserver<ResponseMessage> chatResponseStreamObserver = new StreamObserver<ResponseMessage>() {
            @Override
            public void onNext(ResponseMessage chatResponse) {
                log.info("[DoubleStreamClient] onNext...MSG: {}", chatResponse.getRspMsg());
            }

            @Override
            public void onError(Throwable t) {
                log.warn("[DoubleStreamClient] gRPC request error", t);
            }

            @Override
            public void onCompleted() {
                log.info("[DoubleStreamClient] onCompleted");
            }
        };

        GrpcStreamServiceGrpc.GrpcStreamServiceStub bidirectionalStreamStub = GrpcStreamServiceGrpc.newStub(channel);

        while (true) {
            StreamObserver<RequestMessage> requestStreamObserver = bidirectionalStreamStub.bidirectionalStreamRpc(
                chatResponseStreamObserver);

            Scanner scanner = new Scanner(System.in);
            for (; true; ) {
                String str = scanner.nextLine();
                if (str.equals("EOF")) {
                    requestStreamObserver.onCompleted();
                    break;
                }
                try {
                    requestStreamObserver.onNext(RequestMessage.newBuilder().setReqMsg(str).build());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}

package com.github.leleact.jtest.grpc.stream;

import com.github.leleact.jtest.grpc.api.GrpcStreamServiceGrpc;
import com.github.leleact.jtest.grpc.api.RequestMessage;
import com.github.leleact.jtest.grpc.api.ResponseMessage;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * double stream client
 *
 * @author leleact
 * @since 2023-10-12
 */
@Slf4j
public class UnaryClient {
    public static void main(String[] args) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 10881)
                                                                       .usePlaintext()
                                                                       .intercept(new LogClientInterceptor());
        ManagedChannel channel = channelBuilder.build();
        GrpcStreamServiceGrpc.GrpcStreamServiceFutureStub futureStub = GrpcStreamServiceGrpc.newFutureStub(channel);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("EOF")) {
                break;
            }
            try {
                ListenableFuture<ResponseMessage> future = futureStub.unaryRpc(
                    RequestMessage.newBuilder().setReqMsg(str).build());
                future.get(1, TimeUnit.SECONDS);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}


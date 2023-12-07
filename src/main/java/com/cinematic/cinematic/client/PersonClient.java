package com.cinematic.cinematic.client;

import com.cinematic.grpcperson.PersonRequest;
import com.cinematic.grpcperson.PersonResponse;
import com.cinematic.grpcperson.PersonServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Slf4j
public class PersonClient {

    @GrpcClient("grpcperson")
    private PersonServiceGrpc.PersonServiceBlockingStub blockingStub;

    public void getPerson(String name, String surname) throws StatusRuntimeException{
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        PersonRequest request = PersonRequest.newBuilder()
                .setName(name)
                .setSurname(surname)
                .build();

        PersonResponse response = blockingStub.getPerson(request);

        log.info("Received response for " + name + " " + surname + ": " + response.getMessage());

        // Chiudi il canale gRPC
        channel.shutdown();
    }
}

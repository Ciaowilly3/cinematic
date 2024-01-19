package com.cinematic.cinematic.client;

import com.cinematic.grpcperson.PersonRequest;
import com.cinematic.grpcperson.PersonResponse;
import com.cinematic.grpcperson.PersonServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonClient {
    @GrpcClient("person-client")
    private PersonServiceGrpc.PersonServiceBlockingStub blockingStub;


    public PersonResponse getPerson(String name, String surname) throws StatusRuntimeException{
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        PersonRequest request = PersonRequest.newBuilder()
                .setName(name)
                .setSurname(surname)
                .build();

        blockingStub =  PersonServiceGrpc.newBlockingStub(managedChannel);

        PersonResponse response = blockingStub.getPerson(request);

        log.info("Received response for " + name + " " + surname + ": " + response);

        return response;
    }
}

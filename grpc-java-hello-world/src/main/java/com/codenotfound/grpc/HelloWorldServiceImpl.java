package com.codenotfound.grpc;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc;
import com.codenotfound.grpc.helloworld.Person;
import io.grpc.stub.StreamObserver;

import javax.annotation.Nullable;

@GRpcService
public class HelloWorldServiceImpl
        extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    @Override
    public void sayHello(Person person,
                         StreamObserver<Greeting> responseObserver) {
        LOGGER.info("server received {}", person);

        String message = "Hello " + getOneOf(person) + "!";

        Greeting greeting = Greeting.newBuilder().setMessage(message).build();
        LOGGER.info("server responded {}", greeting);

        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }

    private String getOneOf(@Nullable Person person) {
        if (person == null) return "";

        switch (person.getAskarMassalimovCase()) {
            case FIRST_NAME:
                return person.getFirstName();
            case LAST_NAME:
                return person.getLastName();
            case ASKARMASSALIMOV_NOT_SET:
            default:
                return "";
        }
    }
}

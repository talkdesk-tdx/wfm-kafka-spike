package com.td.wfm.kafka;

import io.smallrye.reactive.messaging.annotations.Merge;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Subscriber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@ApplicationScoped
public class EventConsumer {

    @Inject
    EventConsumerService service;

    @Incoming("strings")
    @Merge
    public void stringSink(final String message) throws InterruptedException {
        service.sinkString(message);
    }

//    @Incoming("strings")
//    public CompletionStage<Void> stringSink(final Message<String> message) {
//        return CompletableFuture.runAsync(() -> {
//            log.info("[in consumer]: {}", Thread.currentThread().toString());
//            service.sinkString(message.getPayload());
//        });
//    }
}

package com.td.wfm.kafka;

import io.quarkus.arc.AlternativePriority;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.annotations.Merge;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Slf4j
public class EventConsumerTest {

    @Inject
    @Channel("write-string")
    Emitter<String> stringEmitter;

    @Inject
    TestEventConsumerService service;

    @Test
    public void testConsumeString() throws InterruptedException {
        log.info("[Thread id in test]: {}", Thread.currentThread().getId());
        final String value = "This is a test string";

        stringEmitter.send(value);

        assertTrue(service.hasString(value, 3));
    }

}

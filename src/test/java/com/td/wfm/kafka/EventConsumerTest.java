package com.td.wfm.kafka;

import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.awaitility.Awaitility.await;

@QuarkusTest
@Slf4j
public class EventConsumerTest {

    @Inject
    @Channel("write-string")
    Emitter<String> emitter;

    @Inject
    TestEventConsumerService service;

    @Test
    public void testConsumeString() throws InterruptedException {
        log.info("[Thread id in test]: {}", Thread.currentThread().getId());
        final String value = "This is a test string";

        emitter.send(value);

        await()
            .atMost(Duration.of(3, ChronoUnit.SECONDS))
            .until(() -> service.getStrings().contains(value));
    }

}

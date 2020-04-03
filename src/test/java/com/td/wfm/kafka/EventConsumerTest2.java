//package com.td.wfm.kafka;
//
//import io.quarkus.test.junit.QuarkusTest;
//import lombok.extern.slf4j.Slf4j;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
//import org.junit.jupiter.api.Test;
//
//import javax.inject.Inject;
//import java.util.concurrent.TimeUnit;
//
//@QuarkusTest
//@Slf4j
//public class EventConsumerTest2 {
//
//    @Inject
//    @Channel("write-string")
//    Emitter<String> stringEmitter;
//
//    @Test
//    public void testConsumeString() throws InterruptedException {
//        stringEmitter.send("test-string");
//
//        TimeUnit.SECONDS.sleep(3);
//    }
//}

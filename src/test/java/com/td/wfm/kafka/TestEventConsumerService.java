package com.td.wfm.kafka;

import io.quarkus.arc.AlternativePriority;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@ApplicationScoped
@AlternativePriority(1)
public class TestEventConsumerService extends EventConsumerService {

    private final CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();

    @Override
    public void sinkString(String value) {
        log.info("[Thread id in consumer]: {}", Thread.currentThread().getId());
        log.info("beforeSinkString");

        super.sinkString(value);

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        strings.add(value);
        synchronized (strings) {
            strings.notifyAll();
        }

        log.info("afterSinkString");
    }

    public boolean hasString(final String value, final int timeout) throws InterruptedException {
        log.info("hasString");
        if (strings.contains(value)) {
            return true;
        }

        synchronized (strings) {
            for (int i = 0; i < timeout; i++) {
                strings.wait(1000);

                log.info("hasString");
                if (strings.contains(value)) {
                    return true;
                }
            }
        }

        return false;
    }
}

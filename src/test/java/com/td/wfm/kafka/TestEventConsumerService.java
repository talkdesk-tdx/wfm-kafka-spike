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

    public CopyOnWriteArrayList<String> getStrings() {
        return strings;
    }

    @Override
    public void sinkString(String value) {
        log.info("[Thread id in consumer]: {}", Thread.currentThread().getId());
        log.info("beforeSinkString");

        super.sinkString(value);

        strings.add(value);
        log.info("Strings add: " + strings.size());

        log.info("afterSinkString");
    }
}

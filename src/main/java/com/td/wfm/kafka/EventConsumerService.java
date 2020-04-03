package com.td.wfm.kafka;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@Slf4j
@ApplicationScoped
public class EventConsumerService {
    public void sinkString(final String value) {
        log.info(value);
    }
}

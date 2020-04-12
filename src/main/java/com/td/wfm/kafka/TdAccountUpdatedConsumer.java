package com.td.wfm.kafka;

import io.quarkus.runtime.ShutdownEvent;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@ApplicationScoped
public class TdAccountUpdatedConsumer {

    private final Map<Integer, Set<String>> accountsPerPartition = new HashMap<>();
    private ExecutorService executor;

    @PostConstruct
    void postConstruct() {
        executor = Executors.newSingleThreadExecutor();
    }

    void onShutdownEvent(@Observes final ShutdownEvent event) {
        executor.shutdown();
        accountsPerPartition.forEach((k, v) -> {
            log.info("[P:{}] A:{}", k, v);
        });
    }

    @Incoming("account_updated")
    public CompletionStage<Void> accountUpdated(final KafkaRecord<String, AccountUpdated> message) {
        return CompletableFuture.runAsync(() -> {
            // message -> getTopic(), getPartition(), getHeaders(), getTimestamp(), getKey(), getPayload()
            log.info("[{}] {}", message.getPartition(), message.getPayload());

            synchronized (accountsPerPartition) {
                final int partition = message.getPartition();
                final String account = message.getPayload().getAccountId();

                if (!accountsPerPartition.containsKey(partition)) {
                    accountsPerPartition.put(partition, new HashSet<>());
                }
                accountsPerPartition.get(partition).add(account);
            }
        }, executor); // TODO use a container-managed threadpool
    }
}

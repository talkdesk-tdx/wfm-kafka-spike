package com.td.wfm.kafka.serialization;

import com.td.wfm.kafka.AccountUpdated;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class AccountUpdatedDeserializer extends JsonbDeserializer<AccountUpdated> {
    public AccountUpdatedDeserializer() {
        super(AccountUpdated.class);
    }
}

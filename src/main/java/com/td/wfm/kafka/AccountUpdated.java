package com.td.wfm.kafka;

import lombok.*;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class AccountUpdated {
    @JsonbProperty("account_id")
    private String accountId;
    @JsonbProperty("event_type")
    private String eventType;
    @JsonbProperty("event_id")
    private String eventId;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Instant timestamp;
}

package com.jaitechltd.bookservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;

import java.util.function.BiConsumer;

@Slf4j
public class KafkaPublisherCallback<M> implements BiConsumer<SendResult<?, M>, Throwable> {

    public KafkaPublisherCallback() {}

    @Override
    public void accept(final SendResult<?, M> sendResult, final Throwable throwable) {
        if (throwable != null) {
            log.error("Failed to send message to topic - ", throwable);
        }
    }
}

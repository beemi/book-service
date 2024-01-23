package com.jaitechltd.bookservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaitechltd.bookservice.config.properties.EventsKafkaProperties;
import com.jaitechltd.bookservice.kafka.KafkaPublisherCallback;
import com.jaitechltd.bookservice.producer.BookProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {

    @Bean
    public BookProducer bookProducer(final KafkaTemplate<String, String> eventKafkaTemplate,
                                     final ObjectMapper objectMapper) {
        return new BookProducer(eventKafkaTemplate, objectMapper);
    }

    @Bean
    public KafkaPublisherCallback<String> kafkaPublisherCallback() {
        return new KafkaPublisherCallback<>();
    }

    @Bean
    public EventsKafkaProperties eventsKafkaProperties() {
        return new EventsKafkaProperties();
    }
}

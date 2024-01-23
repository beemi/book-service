package com.jaitechltd.bookservice.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaitechltd.bookservice.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public BookProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(final String topic, final Book message) {
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.error("Error while sending message to kafka topic: {}", topic, e);
        }
    }
}

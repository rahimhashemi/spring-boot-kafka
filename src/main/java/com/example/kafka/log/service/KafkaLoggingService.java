package com.example.kafka.log.service;

import org.springframework.stereotype.Service;

@Service
public class KafkaLoggingService {

    private final KafkaProducer kafkaProducer;

    public KafkaLoggingService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void logUserAction(String user, String action) {
        String logMessage = user + " performed action: " + action;
        kafkaProducer.sendMessage("user-actions-topic", logMessage);
    }
}

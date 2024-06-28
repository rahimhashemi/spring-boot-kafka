package com.example.kafka.log.config;

import com.example.kafka.log.service.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileLog {

    private static final Logger logger = LoggerFactory.getLogger(FileLog.class);
    
    private final KafkaProducer kafkaProducer;

    public FileLog(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void log(String message) {
        logger.info(message);
//        kafkaProducer.sendMessage("file-log-topic", message);
    }
}

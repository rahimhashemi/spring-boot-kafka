package com.example.kafka.log.controller;

import com.example.kafka.log.aspect.KafkaLoggable;
import com.example.kafka.log.dto.DepositRequest;
import com.example.kafka.log.aspect.FileLoggable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @FileLoggable
    @KafkaLoggable
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request) {
        return ResponseEntity.ok("Deposited successfully for " + request.getAccNo());
    }
}

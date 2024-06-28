package com.example.kafka.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {
    private int id;
    private Long accNo;
    private Long amount;
}

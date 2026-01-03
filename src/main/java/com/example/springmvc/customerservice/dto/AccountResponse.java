package com.example.springmvc.customerservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record AccountResponse(
        Integer id,
        String accountNumber,
        String accountType,
        BigDecimal balance,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer userId,
        String userName,
        Integer customerId,
        String customerName
) {
}


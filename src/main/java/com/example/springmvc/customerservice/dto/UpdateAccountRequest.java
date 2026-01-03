package com.example.springmvc.customerservice.dto;

import java.math.BigDecimal;

public record UpdateAccountRequest(
        String accountType,
        BigDecimal balance,
        String status,
        Integer userId,
        Integer customerId
) {
}


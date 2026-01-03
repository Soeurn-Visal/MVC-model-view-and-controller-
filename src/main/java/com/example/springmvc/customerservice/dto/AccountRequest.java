package com.example.springmvc.customerservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AccountRequest(
        @NotBlank(message = "Account number is required")
        String accountNumber,

        @NotBlank(message = "Account type is required")
        String accountType,

        BigDecimal balance,

        String status,

        @NotNull(message = "User ID is required")
        Integer userId,
        @NotNull(message = "Customer ID is required")
        Integer customerId
) {
}


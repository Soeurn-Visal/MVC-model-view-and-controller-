package com.example.springmvc.customerservice.dto;

public record UpdateCustomerRequest(
        String fullName,
        String gender
) {
}

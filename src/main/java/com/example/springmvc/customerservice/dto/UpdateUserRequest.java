package com.example.springmvc.customerservice.dto;

public record UpdateUserRequest(
        String phoneNumber,
        String fullName,
        String email
) {
}

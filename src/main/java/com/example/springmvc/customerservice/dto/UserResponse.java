package com.example.springmvc.customerservice.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(
        Integer id,
        String phoneNumber,
        String fullName,
        String gender,
        String email,
        LocalDate dob
) {
}

package com.example.springmvc.customerservice.dto;



import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerResponse(
        String id,
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        LocalDate dob

) {
}
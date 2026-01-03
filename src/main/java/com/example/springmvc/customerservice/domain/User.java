package com.example.springmvc.customerservice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto-incrementing ID
    private Integer id;
    private String fullName;
    private String email;
    private String gender;
    private String phoneNumber;
    private LocalDate dob;

}

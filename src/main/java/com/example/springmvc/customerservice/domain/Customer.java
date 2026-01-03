package com.example.springmvc.customerservice.domain;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto-incrementing ID
    private Integer id;

    @Column(nullable = false)
    private String fullName; // full_name

    @Column(length = 15, nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

}
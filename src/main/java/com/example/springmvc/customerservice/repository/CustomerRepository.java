package com.example.springmvc.customerservice.repository;


import com.example.springmvc.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    // SELECT * FROM customers WHERE phone_number = ?1;
    Optional<Customer> findByPhoneNumber(String phoneNumber);

}

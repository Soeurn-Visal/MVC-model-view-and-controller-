package com.example.springmvc.customerservice.repository;

import com.example.springmvc.customerservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    List<Account> findByUserId(Integer userId);

    List<Account> findByCustomerId(Integer customerId);

    List<Account> findByStatus(String status);
}


package com.example.springmvc.customerservice.controller;

import com.example.springmvc.customerservice.dto.AccountRequest;
import com.example.springmvc.customerservice.dto.AccountResponse;
import com.example.springmvc.customerservice.dto.UpdateAccountRequest;
import com.example.springmvc.customerservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    // Create new account
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponse create(@Valid @RequestBody AccountRequest accountRequest) {
        return accountService.createNew(accountRequest);
    }

    // Get all accounts
    @GetMapping
    public List<AccountResponse> getAll() {
        return accountService.findAll();
    }

    // Get account by ID
    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Integer id) {
        return accountService.findById(id);
    }

    // Get account by account number
    @GetMapping("/number/{accountNumber}")
    public AccountResponse getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    // Get accounts by user ID
    @GetMapping("/user/{userId}")
    public List<AccountResponse> getByUserId(@PathVariable Integer userId) {
        return accountService.findByUserId(userId);
    }

    // Get accounts by customer ID
    @GetMapping("/customer/{customerId}")
    public List<AccountResponse> getByCustomerId(@PathVariable Integer customerId) {
        return accountService.findByCustomerId(customerId);
    }

    // Get accounts by status
    @GetMapping("/status/{status}")
    public List<AccountResponse> getByStatus(@PathVariable String status) {
        return accountService.findByStatus(status);
    }

    // Update account
    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable Integer id,
                                   @Valid @RequestBody UpdateAccountRequest updateAccountRequest) {
        return accountService.updateById(id, updateAccountRequest);
    }

    // Delete account by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        accountService.deleteById(id);
    }

    // Delete account by account number
    @DeleteMapping("/number/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAccountNumber(@PathVariable String accountNumber) {
        accountService.deleteByAccountNumber(accountNumber);
    }
}


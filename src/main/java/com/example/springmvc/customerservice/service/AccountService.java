package com.example.springmvc.customerservice.service;

import com.example.springmvc.customerservice.dto.AccountRequest;
import com.example.springmvc.customerservice.dto.AccountResponse;
import com.example.springmvc.customerservice.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createNew(AccountRequest accountRequest);

    List<AccountResponse> findAll();

    AccountResponse findById(Integer id);

    AccountResponse updateById(Integer id, UpdateAccountRequest updateAccountRequest);

    AccountResponse findByAccountNumber(String accountNumber);

    List<AccountResponse> findByUserId(Integer userId);

    List<AccountResponse> findByCustomerId(Integer customerId);

    List<AccountResponse> findByStatus(String status);

    void deleteById(Integer id);

    void deleteByAccountNumber(String accountNumber);
}


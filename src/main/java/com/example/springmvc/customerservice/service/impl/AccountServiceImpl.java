package com.example.springmvc.customerservice.service.impl;

import com.example.springmvc.customerservice.domain.Account;
import com.example.springmvc.customerservice.domain.Customer;
import com.example.springmvc.customerservice.domain.User;
import com.example.springmvc.customerservice.dto.AccountRequest;
import com.example.springmvc.customerservice.dto.AccountResponse;
import com.example.springmvc.customerservice.dto.UpdateAccountRequest;
import com.example.springmvc.customerservice.mapper.AccountMapper;
import com.example.springmvc.customerservice.repository.AccountRepository;
import com.example.springmvc.customerservice.repository.CustomerRepository;
import com.example.springmvc.customerservice.repository.UserRepository;
import com.example.springmvc.customerservice.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createNew(AccountRequest accountRequest) {
        // Validate account number uniqueness
        if (accountRepository.existsByAccountNumber(accountRequest.accountNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account number already exists");
        }

        log.info("Creating account: {}", accountRequest);
        Account account = accountMapper.toDomain(accountRequest);

        // Set User relationship if userId is provided
        if (accountRequest.userId() != null) {
            User user = userRepository.findById(accountRequest.userId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            account.setUser(user);
        }

        // Set Customer relationship if customerId is provided
        if (accountRequest.customerId() != null) {
            Customer customer = customerRepository.findById(accountRequest.customerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            account.setCustomer(customer);
        }

        // Ensure at least one relationship exists
        if (account.getUser() == null && account.getCustomer() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Account must be associated with either a User or a Customer");
        }

        Account savedAccount = accountRepository.save(account);
        log.info("Account created with ID: {}", savedAccount.getId());
        return accountMapper.toAccountResponse(savedAccount);
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    public AccountResponse findById(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse updateById(Integer id, UpdateAccountRequest updateAccountRequest) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        // Update User relationship if userId is provided
        if (updateAccountRequest.userId() != null) {
            User user = userRepository.findById(updateAccountRequest.userId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            account.setUser(user);
        }

        // Update Customer relationship if customerId is provided
        if (updateAccountRequest.customerId() != null) {
            Customer customer = customerRepository.findById(updateAccountRequest.customerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            account.setCustomer(customer);
        }

        accountMapper.toAccountPartially(updateAccountRequest, account);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(updatedAccount);
    }

    @Override
    public AccountResponse findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountMapper::toAccountResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account with number " + accountNumber + " not found"));
    }

    @Override
    public List<AccountResponse> findByUserId(Integer userId) {
        // Validate user exists
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return accountRepository.findByUserId(userId)
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    public List<AccountResponse> findByCustomerId(Integer customerId) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    public List<AccountResponse> findByStatus(String status) {
        return accountRepository.findByStatus(status)
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
        accountRepository.deleteById(id);
        log.info("Account deleted with ID: {}", id);
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account with number " + accountNumber + " not found"));
        accountRepository.delete(account);
        log.info("Account deleted with account number: {}", accountNumber);
    }
}


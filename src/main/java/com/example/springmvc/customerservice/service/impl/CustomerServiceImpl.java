package com.example.springmvc.customerservice.service.impl;

import com.example.springmvc.customerservice.domain.Customer;
import com.example.springmvc.customerservice.dto.CustomerRequest;
import com.example.springmvc.customerservice.dto.CustomerResponse;
import com.example.springmvc.customerservice.dto.UpdateCustomerRequest;
import com.example.springmvc.customerservice.mapper.CustomerMapper;
import com.example.springmvc.customerservice.repository.CustomerRepository;
import com.example.springmvc.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createNew(CustomerRequest customerRequest) {
        // 🔐 validation phone number
        if (customerRepository.existsByPhoneNumber(customerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }
        // Validation Email
        if (customerRepository.existsByEmail(customerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        log.info("Customer before saving: {}", customerRequest);
        Customer customer = customerMapper.toDomain(customerRequest);
        log.info("Customer after saving: {}", customerRequest);

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> findAll() {
        return  customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    @Override
    public CustomerResponse findById(Integer id) {
      Customer customer =  customerRepository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return customerMapper.toCustomerResponse(customer);
    }


    @Override
    public CustomerResponse updateById(Integer id, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerMapper.toCustomerPartially(updateCustomerRequest, customer);
        // customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));
    }

    @Override
    public void deleteById(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer ID not found");
        }
        customerRepository.deleteById(id);

    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));
        customerRepository.delete(customer);

    }
}

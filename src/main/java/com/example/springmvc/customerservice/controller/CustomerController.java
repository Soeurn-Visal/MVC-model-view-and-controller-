package com.example.springmvc.customerservice.controller;

import com.example.springmvc.customerservice.dto.CustomerRequest;
import com.example.springmvc.customerservice.dto.CustomerResponse;
import com.example.springmvc.customerservice.dto.UpdateCustomerRequest;
import com.example.springmvc.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    // create new customer
    @PostMapping
    public CustomerResponse createNew(@RequestBody CustomerRequest customerRequest) {
        return customerService.createNew(customerRequest);
    }

    // get all customers
    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    // get customer by id
    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Integer id) {
        return customerService.findById(id);
    }

    // update customer by id
    @PutMapping("/{id}")
    public CustomerResponse updateById(@PathVariable Integer id, @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateById(id, updateCustomerRequest);

    }
    // get customer by phone number
    @GetMapping("/phone/{phoneNumber}")
    public CustomerResponse findByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }
    // delete customer by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        customerService.deleteById(id);
    }
    // delete customer by phone number
    @DeleteMapping("/phone/{phoneNumber}")
    public void deleteByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.deleteByPhoneNumber(phoneNumber);
    }
}

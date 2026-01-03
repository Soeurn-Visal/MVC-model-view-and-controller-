package com.example.springmvc.customerservice.service;

import com.example.springmvc.customerservice.dto.CustomerRequest;
import com.example.springmvc.customerservice.dto.CustomerResponse;
import com.example.springmvc.customerservice.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    CustomerResponse createNew(CustomerRequest customerRequest);

    List<CustomerResponse> findAll();

    CustomerResponse findById(Integer id);

    CustomerResponse updateById(Integer id, UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse findByPhoneNumber(String phoneNumber);

    void deleteById(Integer id);

    void deleteByPhoneNumber(String phoneNumber);

}

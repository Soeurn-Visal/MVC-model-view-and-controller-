package com.example.springmvc.customerservice.service;

import com.example.springmvc.customerservice.dto.CustomerRequest;
import com.example.springmvc.customerservice.dto.CustomerResponse;
import com.example.springmvc.customerservice.dto.UserRequest;
import com.example.springmvc.customerservice.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createNew(UserRequest userRequest);
    List<UserResponse> getAll();
    UserResponse update(UserRequest userRequest);
    UserResponse findById(Integer id);

    void deleteById(Integer id);






}

package com.example.springmvc.customerservice.service;

import com.example.springmvc.customerservice.dto.*;

import java.util.List;

public interface UserService {

    UserResponse createNew(UserRequest userRequest);
    List<UserResponse> getAll();
    UserResponse updateById(Integer id, UpdateUserRequest updateUserRequest);
    UserResponse findById(Integer id);

    void deleteById(Integer id);






}

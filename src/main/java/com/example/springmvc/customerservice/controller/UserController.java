package com.example.springmvc.customerservice.controller;

import com.example.springmvc.customerservice.dto.UpdateUserRequest;
import com.example.springmvc.customerservice.dto.UserRequest;
import com.example.springmvc.customerservice.dto.UserResponse;
import com.example.springmvc.customerservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // create user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        return userService.createNew(userRequest);
    }

    // get all users
    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    // update user
    @PutMapping("/{id}")
    public UserResponse updateById(@PathVariable Integer id , @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateById(id, updateUserRequest);

    }

    //  get user by id
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);

    }
}
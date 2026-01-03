package com.example.springmvc.customerservice.service.impl;

import com.example.springmvc.customerservice.domain.User;

import com.example.springmvc.customerservice.dto.UpdateUserRequest;
import com.example.springmvc.customerservice.dto.UserRequest;
import com.example.springmvc.customerservice.dto.UserResponse;
import com.example.springmvc.customerservice.mapper.UserMapper;
import com.example.springmvc.customerservice.repository.UserRepository;
import com.example.springmvc.customerservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;



    @Override
    public UserResponse createNew(UserRequest userRequest) {
        // Validation by email
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        // Validation by phone number
        if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }


        log.info("User created: {}", userRequest);
        User user = userMapper.toDomain(userRequest);
       // user.setId(UUID.randomUUID().toString()); // Generate UUID for user ID
        user = userRepository.save(user);
        log.info("User saved with ID: {}", user.getId());
        return userMapper.toUserResponse(user);


    }

    @Override
    public List<UserResponse> getAll() {
        return  userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse updateById(Integer id , UpdateUserRequest updateUserRequest) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        userMapper.toUserPartially(updateUserRequest , user);
        return userMapper.toUserResponse(userRepository.save(user));

    }


    @Override
    public UserResponse findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toUserResponse(user);

    }

    @Override
    public void deleteById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);

    }
}

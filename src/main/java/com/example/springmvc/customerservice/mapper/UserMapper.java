package com.example.springmvc.customerservice.mapper;


import com.example.springmvc.customerservice.domain.User;

import com.example.springmvc.customerservice.dto.UpdateUserRequest;
import com.example.springmvc.customerservice.dto.UserRequest;
import com.example.springmvc.customerservice.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void toUserPartially(UpdateUserRequest updateUserRequest,
                         @MappingTarget User user);  // Partially update

    UserResponse toUserResponse(User user);  // from Domain to Response

    User toDomain(UserRequest userRequest);  // from Request to Domain

}


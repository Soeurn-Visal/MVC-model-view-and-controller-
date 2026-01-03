package com.example.springmvc.customerservice.mapper;


import com.example.springmvc.customerservice.domain.User;

import com.example.springmvc.customerservice.dto.UserRequest;
import com.example.springmvc.customerservice.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse  toUserResponse(User user);  // from Domain to Response

//    @Mapping(target = "phone", source = "phone")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "age", ignore = true)
    User toDomain(UserRequest userRequest);  // from Request to Domain



}

package com.example.springmvc.customerservice.mapper;

import com.example.springmvc.customerservice.domain.Customer;
import com.example.springmvc.customerservice.dto.CustomerRequest;
import com.example.springmvc.customerservice.dto.CustomerResponse;
import com.example.springmvc.customerservice.dto.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    // Partially update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest,
                             @MappingTarget Customer customer);

    CustomerResponse toCustomerResponse(Customer customer);  // from Domain to Response

    Customer  toDomain(CustomerRequest customerRequest); // from Request to Domain
}

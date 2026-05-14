package kz.usenkhan.yersultan.it22310.carrental.service.mapper;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.customer.UsenkhanYersultanCustomerResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCustomer;

public class UsenkhanYersultanCustomerMapper {
    public static UsenkhanYersultanCustomerResponse toResponse(UsenkhanYersultanCustomer customer) {
        return UsenkhanYersultanCustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .driversLicenseNumber(customer.getDriversLicenseNumber())
                .build();
    }
}


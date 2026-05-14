package kz.usenkhan.yersultan.it22310.carrental.service.mapper;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.rental.UsenkhanYersultanRentalResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanRental;

public class UsenkhanYersultanRentalMapper {
    public static UsenkhanYersultanRentalResponse toResponse(UsenkhanYersultanRental rental) {
        return UsenkhanYersultanRentalResponse.builder()
                .id(rental.getId())
                .carId(rental.getCar().getId())
                .carPlateNumber(rental.getCar().getPlateNumber())
                .customerId(rental.getCustomer().getId())
                .customerEmail(rental.getCustomer().getEmail())
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .status(rental.getStatus().name())
                .totalPrice(rental.getTotalPrice())
                .createdAt(rental.getCreatedAt())
                .build();
    }
}


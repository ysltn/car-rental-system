package kz.usenkhan.yersultan.it22310.carrental.service.mapper;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.car.UsenkhanYersultanCarResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCar;

public class UsenkhanYersultanCarMapper {
    public static UsenkhanYersultanCarResponse toResponse(UsenkhanYersultanCar car) {
        return UsenkhanYersultanCarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .plateNumber(car.getPlateNumber())
                .dailyRate(car.getDailyRate())
                .status(car.getStatus().name())
                .branchId(car.getBranch().getId())
                .branchName(car.getBranch().getName())
                .createdAt(car.getCreatedAt())
                .updatedAt(car.getUpdatedAt())
                .build();
    }
}


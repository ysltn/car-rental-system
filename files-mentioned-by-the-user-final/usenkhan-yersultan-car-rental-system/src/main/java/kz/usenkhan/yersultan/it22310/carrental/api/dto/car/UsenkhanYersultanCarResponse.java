package kz.usenkhan.yersultan.it22310.carrental.api.dto.car;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class UsenkhanYersultanCarResponse {
    Long id;
    String brand;
    String model;
    Integer year;
    String plateNumber;
    BigDecimal dailyRate;
    String status;
    Long branchId;
    String branchName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}


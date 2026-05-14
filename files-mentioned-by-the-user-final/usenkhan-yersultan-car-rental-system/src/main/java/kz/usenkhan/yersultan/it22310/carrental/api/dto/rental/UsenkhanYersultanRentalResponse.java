package kz.usenkhan.yersultan.it22310.carrental.api.dto.rental;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class UsenkhanYersultanRentalResponse {
    Long id;
    Long carId;
    String carPlateNumber;
    Long customerId;
    String customerEmail;
    LocalDate startDate;
    LocalDate endDate;
    String status;
    BigDecimal totalPrice;
    LocalDateTime createdAt;
}


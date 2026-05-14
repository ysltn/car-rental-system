package kz.usenkhan.yersultan.it22310.carrental.api.dto.payment;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class UsenkhanYersultanPaymentResponse {
    Long id;
    Long rentalId;
    BigDecimal amount;
    String method;
    String status;
    LocalDateTime createdAt;
}


package kz.usenkhan.yersultan.it22310.carrental.api.dto.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class UsenkhanYersultanPaymentCreateRequest {
    @NotNull
    Long rentalId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal amount;

    @NotBlank
    String method;
}


package kz.usenkhan.yersultan.it22310.carrental.api.dto.car;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class UsenkhanYersultanCarCreateRequest {
    @NotBlank
    @Size(max = 60)
    String brand;

    @NotBlank
    @Size(max = 60)
    String model;

    @NotNull
    @Min(1980)
    @Max(2100)
    Integer year;

    @NotBlank
    @Size(max = 20)
    String plateNumber;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal dailyRate;

    @NotNull
    Long branchId;
}


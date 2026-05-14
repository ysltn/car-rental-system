package kz.usenkhan.yersultan.it22310.carrental.api.dto.rental;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UsenkhanYersultanRentalCreateRequest {
    @NotNull
    Long carId;

    @NotNull
    Long customerId;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;
}


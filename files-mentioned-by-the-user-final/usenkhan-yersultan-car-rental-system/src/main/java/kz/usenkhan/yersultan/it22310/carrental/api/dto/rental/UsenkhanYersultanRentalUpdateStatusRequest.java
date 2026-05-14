package kz.usenkhan.yersultan.it22310.carrental.api.dto.rental;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UsenkhanYersultanRentalUpdateStatusRequest {
    @NotBlank
    String status;
}


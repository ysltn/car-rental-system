package kz.usenkhan.yersultan.it22310.carrental.api.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UsenkhanYersultanCustomerCreateRequest {
    @NotBlank
    @Size(max = 60)
    String firstName;

    @NotBlank
    @Size(max = 60)
    String lastName;

    @Email
    @NotBlank
    String email;

    @Size(max = 40)
    String phone;

    @NotBlank
    @Size(max = 60)
    String driversLicenseNumber;
}


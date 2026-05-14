package kz.usenkhan.yersultan.it22310.carrental.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UsenkhanYersultanAuthRegisterRequest {
    @Email
    @NotBlank
    String email;

    @NotBlank
    @Size(min = 8, max = 72)
    String password;
}


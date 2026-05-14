package kz.usenkhan.yersultan.it22310.carrental.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UsenkhanYersultanAuthLoginRequest {
    @Email
    @NotBlank
    String email;

    @NotBlank
    String password;
}


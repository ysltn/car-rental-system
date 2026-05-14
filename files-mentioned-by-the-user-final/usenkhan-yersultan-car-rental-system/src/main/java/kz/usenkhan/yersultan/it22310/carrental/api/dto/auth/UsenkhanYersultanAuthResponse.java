package kz.usenkhan.yersultan.it22310.carrental.api.dto.auth;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsenkhanYersultanAuthResponse {
    String accessToken;
    String tokenType;
    String email;
    String role;
}


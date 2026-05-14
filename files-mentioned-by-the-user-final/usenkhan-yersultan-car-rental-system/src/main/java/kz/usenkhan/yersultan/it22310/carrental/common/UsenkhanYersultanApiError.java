package kz.usenkhan.yersultan.it22310.carrental.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class UsenkhanYersultanApiError {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime timestamp;
    int status;
    String error;
    String message;
    String path;
    Map<String, String> validationErrors;
}


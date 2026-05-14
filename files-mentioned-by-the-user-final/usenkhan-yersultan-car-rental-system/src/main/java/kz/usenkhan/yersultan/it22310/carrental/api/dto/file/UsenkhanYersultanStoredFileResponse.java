package kz.usenkhan.yersultan.it22310.carrental.api.dto.file;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UsenkhanYersultanStoredFileResponse {
    Long id;
    String originalName;
    String contentType;
    Long sizeBytes;
    LocalDateTime createdAt;
}


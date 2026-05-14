package kz.usenkhan.yersultan.it22310.carrental.service.mapper;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.file.UsenkhanYersultanStoredFileResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanStoredFile;

public class UsenkhanYersultanStoredFileMapper {
    public static UsenkhanYersultanStoredFileResponse toResponse(UsenkhanYersultanStoredFile file) {
        return UsenkhanYersultanStoredFileResponse.builder()
                .id(file.getId())
                .originalName(file.getOriginalName())
                .contentType(file.getContentType())
                .sizeBytes(file.getSizeBytes())
                .createdAt(file.getCreatedAt())
                .build();
    }
}


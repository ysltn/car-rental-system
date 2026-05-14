package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.file.UsenkhanYersultanStoredFileResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanStoredFile;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanStoredFileService;
import kz.usenkhan.yersultan.it22310.carrental.service.mapper.UsenkhanYersultanStoredFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class UsenkhanYersultanStoredFileController {
    private final UsenkhanYersultanStoredFileService storedFileService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsenkhanYersultanStoredFileResponse> upload(@RequestPart("file") MultipartFile file) {
        UsenkhanYersultanStoredFile saved = storedFileService.upload(file);
        return ResponseEntity.ok(UsenkhanYersultanStoredFileMapper.toResponse(saved));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanStoredFileResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UsenkhanYersultanStoredFileMapper.toResponse(storedFileService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        UsenkhanYersultanStoredFile meta = storedFileService.findById(id);
        Resource resource = storedFileService.downloadResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + meta.getOriginalName() + "\"")
                .contentType(MediaType.parseMediaType(meta.getContentType()))
                .contentLength(meta.getSizeBytes())
                .body(resource);
    }
}


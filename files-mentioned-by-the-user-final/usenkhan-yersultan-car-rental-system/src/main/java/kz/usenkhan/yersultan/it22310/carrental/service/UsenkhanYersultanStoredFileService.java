package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanBadRequestException;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanNotFoundException;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanStoredFile;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanStoredFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsenkhanYersultanStoredFileService {
    private final UsenkhanYersultanStoredFileRepository storedFileRepository;

    @Value("${app.storage.dir}")
    private String storageDir;

    @Transactional
    public UsenkhanYersultanStoredFile upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new UsenkhanYersultanBadRequestException("file is required");
        }

        Path dir = Path.of(storageDir);
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new UsenkhanYersultanBadRequestException("Cannot create storage directory: " + dir);
        }

        String storageName = UUID.randomUUID() + "-" + sanitize(file.getOriginalFilename());
        Path target = dir.resolve(storageName);

        try {
            file.transferTo(target);
        } catch (IOException e) {
            throw new UsenkhanYersultanBadRequestException("Failed to store file");
        }

        UsenkhanYersultanStoredFile entity = new UsenkhanYersultanStoredFile();
        entity.setOriginalName(file.getOriginalFilename() == null ? "file" : file.getOriginalFilename());
        entity.setContentType(file.getContentType() == null ? "application/octet-stream" : file.getContentType());
        entity.setSizeBytes(file.getSize());
        entity.setStorageName(storageName);

        UsenkhanYersultanStoredFile saved = storedFileRepository.save(entity);
        log.info("File uploaded id={} name={}", saved.getId(), saved.getOriginalName());
        return saved;
    }

    public UsenkhanYersultanStoredFile findById(Long id) {
        return storedFileRepository.findById(id)
                .orElseThrow(() -> new UsenkhanYersultanNotFoundException("File not found: " + id));
    }

    public Resource downloadResource(Long id) {
        UsenkhanYersultanStoredFile file = findById(id);
        Path path = Path.of(storageDir).resolve(file.getStorageName());
        if (!Files.exists(path)) {
            throw new UsenkhanYersultanNotFoundException("Stored file content missing on disk for id=" + id);
        }
        return new FileSystemResource(path);
    }

    private String sanitize(String name) {
        if (name == null) {
            return "file";
        }
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}


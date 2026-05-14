package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.car.UsenkhanYersultanCarCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.car.UsenkhanYersultanCarResponse;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.car.UsenkhanYersultanCarUpdateRequest;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanCarService;
import kz.usenkhan.yersultan.it22310.carrental.service.mapper.UsenkhanYersultanCarMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class UsenkhanYersultanCarController {
    private final UsenkhanYersultanCarService carService;

    @GetMapping
    public ResponseEntity<Page<UsenkhanYersultanCarResponse>> getAll(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) BigDecimal minRate,
            @RequestParam(required = false) BigDecimal maxRate,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(
                carService.findAll(q, brand, status, branchId, minRate, maxRate, pageable)
                        .map(UsenkhanYersultanCarMapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanCarResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UsenkhanYersultanCarMapper.toResponse(carService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsenkhanYersultanCarResponse> create(@Valid @RequestBody UsenkhanYersultanCarCreateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanCarMapper.toResponse(carService.create(req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanCarResponse> update(@PathVariable Long id, @Valid @RequestBody UsenkhanYersultanCarUpdateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanCarMapper.toResponse(carService.update(id, req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


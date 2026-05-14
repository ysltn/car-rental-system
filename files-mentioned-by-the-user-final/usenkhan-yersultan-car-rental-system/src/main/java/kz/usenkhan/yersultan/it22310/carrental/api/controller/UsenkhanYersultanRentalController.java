package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.rental.UsenkhanYersultanRentalCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.rental.UsenkhanYersultanRentalResponse;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.rental.UsenkhanYersultanRentalUpdateStatusRequest;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanRentalService;
import kz.usenkhan.yersultan.it22310.carrental.service.mapper.UsenkhanYersultanRentalMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class UsenkhanYersultanRentalController {
    private final UsenkhanYersultanRentalService rentalService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsenkhanYersultanRentalResponse>> getAll() {
        return ResponseEntity.ok(rentalService.findAll().stream().map(UsenkhanYersultanRentalMapper::toResponse).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanRentalResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UsenkhanYersultanRentalMapper.toResponse(rentalService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UsenkhanYersultanRentalResponse> create(@Valid @RequestBody UsenkhanYersultanRentalCreateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanRentalMapper.toResponse(rentalService.create(req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<UsenkhanYersultanRentalResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UsenkhanYersultanRentalUpdateStatusRequest req
    ) {
        return ResponseEntity.ok(UsenkhanYersultanRentalMapper.toResponse(rentalService.updateStatus(id, req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rentalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


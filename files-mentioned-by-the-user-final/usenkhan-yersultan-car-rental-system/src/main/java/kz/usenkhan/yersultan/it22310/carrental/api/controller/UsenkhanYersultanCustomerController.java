package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.customer.UsenkhanYersultanCustomerCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.customer.UsenkhanYersultanCustomerResponse;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.customer.UsenkhanYersultanCustomerUpdateRequest;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanCustomerService;
import kz.usenkhan.yersultan.it22310.carrental.service.mapper.UsenkhanYersultanCustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class UsenkhanYersultanCustomerController {
    private final UsenkhanYersultanCustomerService customerService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsenkhanYersultanCustomerResponse>> getAll() {
        return ResponseEntity.ok(customerService.findAll().stream().map(UsenkhanYersultanCustomerMapper::toResponse).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanCustomerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UsenkhanYersultanCustomerMapper.toResponse(customerService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsenkhanYersultanCustomerResponse> create(@Valid @RequestBody UsenkhanYersultanCustomerCreateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanCustomerMapper.toResponse(customerService.create(req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanCustomerResponse> update(@PathVariable Long id, @Valid @RequestBody UsenkhanYersultanCustomerUpdateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanCustomerMapper.toResponse(customerService.update(id, req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


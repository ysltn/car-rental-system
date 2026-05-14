package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.payment.UsenkhanYersultanPaymentCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.payment.UsenkhanYersultanPaymentResponse;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanPaymentService;
import kz.usenkhan.yersultan.it22310.carrental.service.mapper.UsenkhanYersultanPaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class UsenkhanYersultanPaymentController {
    private final UsenkhanYersultanPaymentService paymentService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsenkhanYersultanPaymentResponse>> getAll() {
        return ResponseEntity.ok(paymentService.findAll().stream().map(UsenkhanYersultanPaymentMapper::toResponse).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanPaymentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UsenkhanYersultanPaymentMapper.toResponse(paymentService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsenkhanYersultanPaymentResponse> create(@Valid @RequestBody UsenkhanYersultanPaymentCreateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanPaymentMapper.toResponse(paymentService.create(req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


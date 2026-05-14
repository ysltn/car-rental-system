package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.branch.UsenkhanYersultanBranchCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.branch.UsenkhanYersultanBranchResponse;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.branch.UsenkhanYersultanBranchUpdateRequest;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanBranchService;
import kz.usenkhan.yersultan.it22310.carrental.service.mapper.UsenkhanYersultanBranchMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class UsenkhanYersultanBranchController {
    private final UsenkhanYersultanBranchService branchService;

    @GetMapping
    public ResponseEntity<List<UsenkhanYersultanBranchResponse>> getAll() {
        return ResponseEntity.ok(branchService.findAll().stream().map(UsenkhanYersultanBranchMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanBranchResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UsenkhanYersultanBranchMapper.toResponse(branchService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsenkhanYersultanBranchResponse> create(@Valid @RequestBody UsenkhanYersultanBranchCreateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanBranchMapper.toResponse(branchService.create(req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UsenkhanYersultanBranchResponse> update(@PathVariable Long id, @Valid @RequestBody UsenkhanYersultanBranchUpdateRequest req) {
        return ResponseEntity.ok(UsenkhanYersultanBranchMapper.toResponse(branchService.update(id, req)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


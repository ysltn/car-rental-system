package kz.usenkhan.yersultan.it22310.carrental.api.controller;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.auth.UsenkhanYersultanAuthLoginRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.auth.UsenkhanYersultanAuthRegisterRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.auth.UsenkhanYersultanAuthResponse;
import kz.usenkhan.yersultan.it22310.carrental.service.UsenkhanYersultanAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UsenkhanYersultanAuthController {
    private final UsenkhanYersultanAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UsenkhanYersultanAuthResponse> register(@Valid @RequestBody UsenkhanYersultanAuthRegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<UsenkhanYersultanAuthResponse> login(@Valid @RequestBody UsenkhanYersultanAuthLoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
}


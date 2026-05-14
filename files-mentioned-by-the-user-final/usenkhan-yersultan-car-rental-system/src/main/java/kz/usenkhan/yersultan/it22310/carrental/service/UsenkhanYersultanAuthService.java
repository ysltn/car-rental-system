package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.auth.UsenkhanYersultanAuthLoginRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.auth.UsenkhanYersultanAuthRegisterRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.auth.UsenkhanYersultanAuthResponse;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanBadRequestException;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanUserRole;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanAppUser;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanAppUserRepository;
import kz.usenkhan.yersultan.it22310.carrental.security.UsenkhanYersultanJwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsenkhanYersultanAuthService {
    private final UsenkhanYersultanAppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsenkhanYersultanJwtUtil jwtUtil;

    @Transactional
    public UsenkhanYersultanAuthResponse register(UsenkhanYersultanAuthRegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new UsenkhanYersultanBadRequestException("Email already exists: " + req.getEmail());
        }
        UsenkhanYersultanAppUser user = new UsenkhanYersultanAppUser();
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(UsenkhanYersultanUserRole.ROLE_CUSTOMER);
        UsenkhanYersultanAppUser saved = userRepository.save(user);
        log.info("User registered id={} email={}", saved.getId(), saved.getEmail());
        return issueToken(saved);
    }

    public UsenkhanYersultanAuthResponse login(UsenkhanYersultanAuthLoginRequest req) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (AuthenticationException e) {
            throw new UsenkhanYersultanBadRequestException("Invalid credentials");
        }

        UsenkhanYersultanAppUser user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new UsenkhanYersultanBadRequestException("Invalid credentials"));

        return issueToken(user);
    }

    private UsenkhanYersultanAuthResponse issueToken(UsenkhanYersultanAppUser user) {
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return UsenkhanYersultanAuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}


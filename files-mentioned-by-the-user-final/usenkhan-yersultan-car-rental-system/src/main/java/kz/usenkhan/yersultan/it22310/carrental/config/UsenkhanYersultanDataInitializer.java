package kz.usenkhan.yersultan.it22310.carrental.config;

import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanUserRole;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanAppUser;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanAppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class UsenkhanYersultanDataInitializer {
    private final UsenkhanYersultanAppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            String email = "admin@carrental.local";
            if (userRepository.existsByEmail(email)) {
                return;
            }
            UsenkhanYersultanAppUser admin = new UsenkhanYersultanAppUser();
            admin.setEmail(email);
            admin.setPasswordHash(passwordEncoder.encode("admin12345"));
            admin.setRole(UsenkhanYersultanUserRole.ROLE_ADMIN);
            userRepository.save(admin);
            log.info("Admin user created email={} password={}", email, "admin12345");
        };
    }
}


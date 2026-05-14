package kz.usenkhan.yersultan.it22310.carrental.repository;

import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsenkhanYersultanAppUserRepository extends JpaRepository<UsenkhanYersultanAppUser, Long> {
    Optional<UsenkhanYersultanAppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}


package kz.usenkhan.yersultan.it22310.carrental.domain.entity;

import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanUserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_users")
public class UsenkhanYersultanAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsenkhanYersultanUserRole role;
}


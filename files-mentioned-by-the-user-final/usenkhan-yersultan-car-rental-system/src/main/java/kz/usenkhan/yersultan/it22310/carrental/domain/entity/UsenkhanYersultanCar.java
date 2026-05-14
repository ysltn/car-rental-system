package kz.usenkhan.yersultan.it22310.carrental.domain.entity;

import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanCarStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class UsenkhanYersultanCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal dailyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsenkhanYersultanCarStatus status = UsenkhanYersultanCarStatus.AVAILABLE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private UsenkhanYersultanBranch branch;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}

package kz.usenkhan.yersultan.it22310.carrental.domain.entity;

import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanPaymentMethod;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanPaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class UsenkhanYersultanPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rental_id", nullable = false)
    private UsenkhanYersultanRental rental;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsenkhanYersultanPaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsenkhanYersultanPaymentStatus status = UsenkhanYersultanPaymentStatus.PENDING;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

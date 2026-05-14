package kz.usenkhan.yersultan.it22310.carrental.domain.entity;

import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanRentalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class UsenkhanYersultanRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private UsenkhanYersultanCar car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private UsenkhanYersultanCustomer customer;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsenkhanYersultanRentalStatus status = UsenkhanYersultanRentalStatus.ACTIVE;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

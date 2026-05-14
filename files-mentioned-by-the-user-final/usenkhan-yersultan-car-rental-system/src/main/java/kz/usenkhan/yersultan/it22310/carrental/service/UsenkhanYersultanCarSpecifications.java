package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanCarStatus;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCar;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class UsenkhanYersultanCarSpecifications {
    public static Specification<UsenkhanYersultanCar> withFilters(
            String q,
            String brand,
            UsenkhanYersultanCarStatus status,
            Long branchId,
            BigDecimal minRate,
            BigDecimal maxRate
    ) {
        return Specification.where(search(q))
                .and(brandEquals(brand))
                .and(statusEquals(status))
                .and(branchEquals(branchId))
                .and(rateGte(minRate))
                .and(rateLte(maxRate));
    }

    private static Specification<UsenkhanYersultanCar> search(String q) {
        if (q == null || q.isBlank()) {
            return null;
        }
        String like = "%" + q.toLowerCase() + "%";
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("brand")), like),
                cb.like(cb.lower(root.get("model")), like),
                cb.like(cb.lower(root.get("plateNumber")), like)
        );
    }

    private static Specification<UsenkhanYersultanCar> brandEquals(String brand) {
        if (brand == null || brand.isBlank()) {
            return null;
        }
        return (root, query, cb) -> cb.equal(cb.lower(root.get("brand")), brand.toLowerCase());
    }

    private static Specification<UsenkhanYersultanCar> statusEquals(UsenkhanYersultanCarStatus status) {
        if (status == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    private static Specification<UsenkhanYersultanCar> branchEquals(Long branchId) {
        if (branchId == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("branch").get("id"), branchId);
    }

    private static Specification<UsenkhanYersultanCar> rateGte(BigDecimal minRate) {
        if (minRate == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dailyRate"), minRate);
    }

    private static Specification<UsenkhanYersultanCar> rateLte(BigDecimal maxRate) {
        if (maxRate == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("dailyRate"), maxRate);
    }
}


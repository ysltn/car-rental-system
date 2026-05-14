package kz.usenkhan.yersultan.it22310.carrental.repository;

import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsenkhanYersultanCustomerRepository extends JpaRepository<UsenkhanYersultanCustomer, Long> {
    boolean existsByEmail(String email);
    boolean existsByDriversLicenseNumber(String driversLicenseNumber);
}


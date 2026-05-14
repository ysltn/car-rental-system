package kz.usenkhan.yersultan.it22310.carrental.repository;

import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsenkhanYersultanCarRepository extends JpaRepository<UsenkhanYersultanCar, Long>, JpaSpecificationExecutor<UsenkhanYersultanCar> {
    boolean existsByPlateNumber(String plateNumber);
}


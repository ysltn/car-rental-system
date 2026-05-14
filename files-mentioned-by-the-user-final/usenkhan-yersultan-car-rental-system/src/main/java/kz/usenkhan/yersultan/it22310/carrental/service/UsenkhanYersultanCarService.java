package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.car.UsenkhanYersultanCarCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.car.UsenkhanYersultanCarUpdateRequest;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanBadRequestException;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanNotFoundException;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanCarStatus;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanBranch;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCar;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanCarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsenkhanYersultanCarService {
    private final UsenkhanYersultanCarRepository carRepository;
    private final UsenkhanYersultanBranchService branchService;

    public Page<UsenkhanYersultanCar> findAll(
            String q,
            String brand,
            String status,
            Long branchId,
            BigDecimal minRate,
            BigDecimal maxRate,
            Pageable pageable
    ) {
        UsenkhanYersultanCarStatus parsedStatus = parseStatus(status);
        Specification<UsenkhanYersultanCar> spec = UsenkhanYersultanCarSpecifications.withFilters(
                q,
                brand,
                parsedStatus,
                branchId,
                minRate,
                maxRate
        );
        return carRepository.findAll(spec, pageable);
    }

    public UsenkhanYersultanCar findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new UsenkhanYersultanNotFoundException("Car not found: " + id));
    }

    @Transactional
    public UsenkhanYersultanCar create(UsenkhanYersultanCarCreateRequest req) {
        if (carRepository.existsByPlateNumber(req.getPlateNumber())) {
            throw new UsenkhanYersultanBadRequestException("Car with plateNumber already exists: " + req.getPlateNumber());
        }
        UsenkhanYersultanBranch branch = branchService.findById(req.getBranchId());

        UsenkhanYersultanCar car = new UsenkhanYersultanCar();
        car.setBrand(req.getBrand());
        car.setModel(req.getModel());
        car.setYear(req.getYear());
        car.setPlateNumber(req.getPlateNumber());
        car.setDailyRate(req.getDailyRate());
        car.setBranch(branch);
        car.setStatus(UsenkhanYersultanCarStatus.AVAILABLE);

        UsenkhanYersultanCar saved = carRepository.save(car);
        log.info("Car created id={} plate={}", saved.getId(), saved.getPlateNumber());
        return saved;
    }

    @Transactional
    public UsenkhanYersultanCar update(Long id, UsenkhanYersultanCarUpdateRequest req) {
        UsenkhanYersultanCar car = findById(id);
        UsenkhanYersultanBranch branch = branchService.findById(req.getBranchId());

        car.setBrand(req.getBrand());
        car.setModel(req.getModel());
        car.setYear(req.getYear());
        car.setDailyRate(req.getDailyRate());
        car.setBranch(branch);
        car.setStatus(parseStatusRequired(req.getStatus()));
        log.info("Car updated id={}", id);
        return car;
    }

    @Transactional
    public void delete(Long id) {
        if (!carRepository.existsById(id)) {
            throw new UsenkhanYersultanNotFoundException("Car not found: " + id);
        }
        carRepository.deleteById(id);
        log.info("Car deleted id={}", id);
    }

    private UsenkhanYersultanCarStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return null;
        }
        try {
            return UsenkhanYersultanCarStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UsenkhanYersultanBadRequestException("Invalid car status: " + status);
        }
    }

    private UsenkhanYersultanCarStatus parseStatusRequired(String status) {
        UsenkhanYersultanCarStatus parsed = parseStatus(status);
        if (parsed == null) {
            throw new UsenkhanYersultanBadRequestException("Car status is required");
        }
        return parsed;
    }
}


package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.rental.UsenkhanYersultanRentalCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.rental.UsenkhanYersultanRentalUpdateStatusRequest;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanBadRequestException;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanNotFoundException;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanCarStatus;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanRentalStatus;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCar;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCustomer;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanRental;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanRentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsenkhanYersultanRentalService {
    private final UsenkhanYersultanRentalRepository rentalRepository;
    private final UsenkhanYersultanCarService carService;
    private final UsenkhanYersultanCustomerService customerService;
    private final UsenkhanYersultanAsyncTasksService asyncTasksService;

    public List<UsenkhanYersultanRental> findAll() {
        return rentalRepository.findAll();
    }

    public UsenkhanYersultanRental findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new UsenkhanYersultanNotFoundException("Rental not found: " + id));
    }

    @Transactional
    public UsenkhanYersultanRental create(UsenkhanYersultanRentalCreateRequest req) {
        if (req.getEndDate().isBefore(req.getStartDate())) {
            throw new UsenkhanYersultanBadRequestException("endDate must be >= startDate");
        }

        UsenkhanYersultanCar car = carService.findById(req.getCarId());
        if (car.getStatus() != UsenkhanYersultanCarStatus.AVAILABLE) {
            throw new UsenkhanYersultanBadRequestException("Car is not available. status=" + car.getStatus());
        }

        UsenkhanYersultanCustomer customer = customerService.findById(req.getCustomerId());

        long days = ChronoUnit.DAYS.between(req.getStartDate(), req.getEndDate()) + 1;
        BigDecimal total = car.getDailyRate().multiply(BigDecimal.valueOf(days));

        UsenkhanYersultanRental rental = new UsenkhanYersultanRental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setStartDate(req.getStartDate());
        rental.setEndDate(req.getEndDate());
        rental.setStatus(UsenkhanYersultanRentalStatus.ACTIVE);
        rental.setTotalPrice(total);

        car.setStatus(UsenkhanYersultanCarStatus.RENTED);

        UsenkhanYersultanRental saved = rentalRepository.save(rental);
        log.info("Rental created id={} carId={} customerId={}", saved.getId(), car.getId(), customer.getId());

        asyncTasksService.auditLogAsync("Rental created id=" + saved.getId());
        asyncTasksService.generateInvoiceAsync(saved.getId());
        asyncTasksService.notifyCustomerAsync(customer.getEmail(), "Your rental is created. id=" + saved.getId());

        return saved;
    }

    @Transactional
    public UsenkhanYersultanRental updateStatus(Long id, UsenkhanYersultanRentalUpdateStatusRequest req) {
        UsenkhanYersultanRental rental = findById(id);
        UsenkhanYersultanRentalStatus status = parseRentalStatus(req.getStatus());

        rental.setStatus(status);
        if (status == UsenkhanYersultanRentalStatus.COMPLETED || status == UsenkhanYersultanRentalStatus.CANCELLED) {
            rental.getCar().setStatus(UsenkhanYersultanCarStatus.AVAILABLE);
        }
        log.info("Rental status updated id={} status={}", id, status);
        asyncTasksService.auditLogAsync("Rental status updated id=" + id + " status=" + status);
        return rental;
    }

    @Transactional
    public void delete(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new UsenkhanYersultanNotFoundException("Rental not found: " + id);
        }
        rentalRepository.deleteById(id);
        log.info("Rental deleted id={}", id);
    }

    private UsenkhanYersultanRentalStatus parseRentalStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new UsenkhanYersultanBadRequestException("Rental status is required");
        }
        try {
            return UsenkhanYersultanRentalStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UsenkhanYersultanBadRequestException("Invalid rental status: " + status);
        }
    }
}


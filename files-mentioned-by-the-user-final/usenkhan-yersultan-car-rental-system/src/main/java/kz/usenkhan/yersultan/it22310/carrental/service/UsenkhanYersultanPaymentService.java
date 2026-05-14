package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.payment.UsenkhanYersultanPaymentCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanBadRequestException;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanNotFoundException;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanPaymentMethod;
import kz.usenkhan.yersultan.it22310.carrental.domain.UsenkhanYersultanPaymentStatus;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanPayment;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanRental;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsenkhanYersultanPaymentService {
    private final UsenkhanYersultanPaymentRepository paymentRepository;
    private final UsenkhanYersultanRentalService rentalService;

    public List<UsenkhanYersultanPayment> findAll() {
        return paymentRepository.findAll();
    }

    public UsenkhanYersultanPayment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new UsenkhanYersultanNotFoundException("Payment not found: " + id));
    }

    @Transactional
    public UsenkhanYersultanPayment create(UsenkhanYersultanPaymentCreateRequest req) {
        UsenkhanYersultanRental rental = rentalService.findById(req.getRentalId());

        UsenkhanYersultanPaymentMethod method = parseMethod(req.getMethod());

        UsenkhanYersultanPayment payment = new UsenkhanYersultanPayment();
        payment.setRental(rental);
        payment.setAmount(req.getAmount());
        payment.setMethod(method);
        payment.setStatus(UsenkhanYersultanPaymentStatus.PAID);

        UsenkhanYersultanPayment saved = paymentRepository.save(payment);
        log.info("Payment created id={} rentalId={} amount={}", saved.getId(), rental.getId(), saved.getAmount());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new UsenkhanYersultanNotFoundException("Payment not found: " + id);
        }
        paymentRepository.deleteById(id);
        log.info("Payment deleted id={}", id);
    }

    private UsenkhanYersultanPaymentMethod parseMethod(String method) {
        if (method == null || method.isBlank()) {
            throw new UsenkhanYersultanBadRequestException("Payment method is required");
        }
        try {
            return UsenkhanYersultanPaymentMethod.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UsenkhanYersultanBadRequestException("Invalid payment method: " + method);
        }
    }
}


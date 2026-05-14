package kz.usenkhan.yersultan.it22310.carrental.service.mapper;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.payment.UsenkhanYersultanPaymentResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanPayment;

public class UsenkhanYersultanPaymentMapper {
    public static UsenkhanYersultanPaymentResponse toResponse(UsenkhanYersultanPayment payment) {
        return UsenkhanYersultanPaymentResponse.builder()
                .id(payment.getId())
                .rentalId(payment.getRental().getId())
                .amount(payment.getAmount())
                .method(payment.getMethod().name())
                .status(payment.getStatus().name())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}


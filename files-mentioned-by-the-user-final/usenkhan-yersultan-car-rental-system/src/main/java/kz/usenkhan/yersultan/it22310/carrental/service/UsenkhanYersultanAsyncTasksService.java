package kz.usenkhan.yersultan.it22310.carrental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class UsenkhanYersultanAsyncTasksService {
    @Async
    public CompletableFuture<Void> auditLogAsync(String message) {
        log.info("[ASYNC][AUDIT] {}", message);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<String> generateInvoiceAsync(Long rentalId) {
        String invoiceNumber = "INV-" + rentalId + "-" + System.currentTimeMillis();
        log.info("[ASYNC][INVOICE] rentalId={} invoiceNumber={}", rentalId, invoiceNumber);
        return CompletableFuture.completedFuture(invoiceNumber);
    }

    @Async
    public CompletableFuture<Void> notifyCustomerAsync(String email, String text) {
        log.info("[ASYNC][NOTIFY] to={} text={}", email, text);
        return CompletableFuture.completedFuture(null);
    }
}


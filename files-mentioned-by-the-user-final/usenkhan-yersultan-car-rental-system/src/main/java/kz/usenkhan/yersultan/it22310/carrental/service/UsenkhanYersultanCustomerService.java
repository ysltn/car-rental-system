package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.customer.UsenkhanYersultanCustomerCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.customer.UsenkhanYersultanCustomerUpdateRequest;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanBadRequestException;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanNotFoundException;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanCustomer;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsenkhanYersultanCustomerService {
    private final UsenkhanYersultanCustomerRepository customerRepository;

    public List<UsenkhanYersultanCustomer> findAll() {
        return customerRepository.findAll();
    }

    public UsenkhanYersultanCustomer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new UsenkhanYersultanNotFoundException("Customer not found: " + id));
    }

    @Transactional
    public UsenkhanYersultanCustomer create(UsenkhanYersultanCustomerCreateRequest req) {
        if (customerRepository.existsByEmail(req.getEmail())) {
            throw new UsenkhanYersultanBadRequestException("Customer email already exists: " + req.getEmail());
        }
        if (customerRepository.existsByDriversLicenseNumber(req.getDriversLicenseNumber())) {
            throw new UsenkhanYersultanBadRequestException("Drivers license already exists: " + req.getDriversLicenseNumber());
        }
        UsenkhanYersultanCustomer customer = new UsenkhanYersultanCustomer();
        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.setEmail(req.getEmail());
        customer.setPhone(req.getPhone());
        customer.setDriversLicenseNumber(req.getDriversLicenseNumber());
        return customerRepository.save(customer);
    }

    @Transactional
    public UsenkhanYersultanCustomer update(Long id, UsenkhanYersultanCustomerUpdateRequest req) {
        UsenkhanYersultanCustomer customer = findById(id);
        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.setEmail(req.getEmail());
        customer.setPhone(req.getPhone());
        customer.setDriversLicenseNumber(req.getDriversLicenseNumber());
        return customer;
    }

    @Transactional
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new UsenkhanYersultanNotFoundException("Customer not found: " + id);
        }
        customerRepository.deleteById(id);
    }
}


package kz.usenkhan.yersultan.it22310.carrental.api.dto.customer;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsenkhanYersultanCustomerResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String driversLicenseNumber;
}


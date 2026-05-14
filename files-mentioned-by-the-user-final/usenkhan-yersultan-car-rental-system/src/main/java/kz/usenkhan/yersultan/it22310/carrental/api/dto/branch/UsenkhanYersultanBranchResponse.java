package kz.usenkhan.yersultan.it22310.carrental.api.dto.branch;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsenkhanYersultanBranchResponse {
    Long id;
    String name;
    String address;
    String phone;
}


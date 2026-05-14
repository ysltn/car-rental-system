package kz.usenkhan.yersultan.it22310.carrental.api.dto.branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UsenkhanYersultanBranchUpdateRequest {
    @NotBlank
    @Size(max = 120)
    String name;

    @NotBlank
    @Size(max = 255)
    String address;

    @Size(max = 40)
    String phone;
}


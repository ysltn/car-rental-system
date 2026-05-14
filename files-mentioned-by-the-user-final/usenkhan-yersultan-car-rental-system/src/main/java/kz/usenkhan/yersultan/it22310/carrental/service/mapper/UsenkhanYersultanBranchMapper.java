package kz.usenkhan.yersultan.it22310.carrental.service.mapper;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.branch.UsenkhanYersultanBranchResponse;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanBranch;

public class UsenkhanYersultanBranchMapper {
    public static UsenkhanYersultanBranchResponse toResponse(UsenkhanYersultanBranch branch) {
        return UsenkhanYersultanBranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .build();
    }
}


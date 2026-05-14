package kz.usenkhan.yersultan.it22310.carrental.service;

import kz.usenkhan.yersultan.it22310.carrental.api.dto.branch.UsenkhanYersultanBranchCreateRequest;
import kz.usenkhan.yersultan.it22310.carrental.api.dto.branch.UsenkhanYersultanBranchUpdateRequest;
import kz.usenkhan.yersultan.it22310.carrental.common.UsenkhanYersultanNotFoundException;
import kz.usenkhan.yersultan.it22310.carrental.domain.entity.UsenkhanYersultanBranch;
import kz.usenkhan.yersultan.it22310.carrental.repository.UsenkhanYersultanBranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsenkhanYersultanBranchService {
    private final UsenkhanYersultanBranchRepository branchRepository;

    public List<UsenkhanYersultanBranch> findAll() {
        return branchRepository.findAll();
    }

    public UsenkhanYersultanBranch findById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new UsenkhanYersultanNotFoundException("Branch not found: " + id));
    }

    @Transactional
    public UsenkhanYersultanBranch create(UsenkhanYersultanBranchCreateRequest req) {
        UsenkhanYersultanBranch branch = new UsenkhanYersultanBranch();
        branch.setName(req.getName());
        branch.setAddress(req.getAddress());
        branch.setPhone(req.getPhone());
        return branchRepository.save(branch);
    }

    @Transactional
    public UsenkhanYersultanBranch update(Long id, UsenkhanYersultanBranchUpdateRequest req) {
        UsenkhanYersultanBranch branch = findById(id);
        branch.setName(req.getName());
        branch.setAddress(req.getAddress());
        branch.setPhone(req.getPhone());
        return branch;
    }

    @Transactional
    public void delete(Long id) {
        if (!branchRepository.existsById(id)) {
            throw new UsenkhanYersultanNotFoundException("Branch not found: " + id);
        }
        branchRepository.deleteById(id);
    }
}


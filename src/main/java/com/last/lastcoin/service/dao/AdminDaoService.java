package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.AdminEntity;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.AdminRepository;
import com.last.lastcoin.service.BaseService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDaoService extends BaseService {

    private final AdminRepository adminRepository;

    @Transactional
    public AdminEntity createAdmin(AdminEntity adminEntity) {
        if (null == single(adminEntity.getName()))
            throw new CustomResponseStatusException(ExceptionCode.DUPLICATED_MEMBER, "");
        AdminEntity save = adminRepository.save(adminEntity);
        return save;
    }

    public Optional<AdminEntity> single(String name) {
        return adminRepository.findByNameAndDeletedTimeIsNull(name);
    }

    public List<AdminEntity> all(){
        return adminRepository.findAll();
    }
    public Page<AdminEntity> list(Pages.PageRequest pageRequest) {
        Page<AdminEntity> list;
        if (null == pageRequest.getKeyword() || pageRequest.getKeyword().equals("")) {
            list = adminRepository.findAllByDeletedTimeNull(generatePageable(pageRequest));
        } else {
            if (pageRequest.getKeywordType().toLowerCase().equals("name")) {
                list = adminRepository.findAllByNameContaining(pageRequest.getKeyword(),
                        generatePageable(pageRequest));
            } else {
                list = adminRepository.findAllByDeletedTimeNull(generatePageable(pageRequest));
            }
        }
        return list;
    }

    public Long count() {
        return adminRepository.count();
    }
}

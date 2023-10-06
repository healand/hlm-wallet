package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.AdminEntity;
import com.last.lastcoin.domain.AdminLogEntity;
import com.last.lastcoin.dto.AdminLog;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.AdminLogRepository;
import com.last.lastcoin.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdminLogDaoService extends BaseService {
    private final AdminLogRepository adminLogRepository;

    @Transactional
    public AdminLogEntity insertAdminLog(AdminLogEntity adminLogEntity) {
       AdminLogEntity entity = adminLogRepository.save(adminLogEntity);
        return entity;
    }

    public Page<AdminLogEntity> list(Pages.PageRequest pageRequest){
        Page<AdminLogEntity> res;
        if (pageRequest.getKeywordType().equals("admin")){
            res = adminLogRepository.findAllByAdminId(Long.parseLong(pageRequest.getKeyword()),generatePageable(pageRequest.getCurrent(),pageRequest.getPageSize()));
        }else{
             res = adminLogRepository.findAll(generatePageable(pageRequest.getCurrent(),pageRequest.getPageSize()));
        }

        return res;
    }
}

package com.last.lastcoin.service;

import com.last.lastcoin.domain.AdminLogEntity;
import com.last.lastcoin.domain.HistoryEntity;
import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.service.dao.AdminLogDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLogService extends BaseService{
    private final AdminLogDaoService adminLogDaoService;

    public Page<AdminLogEntity> list(Pages.PageRequest pageRequest) {
        Page<AdminLogEntity> res = adminLogDaoService.list(pageRequest);

        return res;
    }
}

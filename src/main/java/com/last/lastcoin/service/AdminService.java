package com.last.lastcoin.service;

import com.last.lastcoin.domain.AdminEntity;
import com.last.lastcoin.domain.AdminLogEntity;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.service.dao.AdminDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDaoService adminDaoService;

    public List<AdminEntity> list() {
        List<AdminEntity> res = adminDaoService.all();
        return res;
    }
}

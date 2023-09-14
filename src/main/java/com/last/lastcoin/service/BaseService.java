package com.last.lastcoin.service;

import com.last.lastcoin.dto.base.Claim;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.utils.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

abstract public class BaseService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Pageable generatePageable(int page, String standardData) {
        return PageRequest.of(page - 1, 10, Sort.by(standardData).descending());
    }

    public Pageable generatePageable(int page, int size, String standardData) {
        return PageRequest.of(page - 1, size, Sort.by(standardData).descending());
    }

    public Pageable generatePageable(int page) {
        return PageRequest.of(page - 1, 10, Sort.by("createdTime").descending());
    }

    public Pageable generatePageable(int page, int size) {
        return PageRequest.of(page - 1, size, Sort.by("createdTime").descending());
    }

    public Pageable generatePageable(Pages.PageRequest pageRequest) {
        return PageRequest.of(pageRequest.getCurrent() - 1, pageRequest.getPageSize(),
                Sort.by("createdTime").descending());
    }

    public Claim self(HttpServletRequest request) {
        return jwtTokenProvider.getClaimsByHeader(request);
    }
}

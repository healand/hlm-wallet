package com.last.lastcoin.service;

import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.dto.Member.MemberWalletUpdateRequest;
import com.last.lastcoin.service.dao.MemberDaoService;
import com.last.lastcoin.service.dao.WalletDaoService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService extends BaseService {

    private final MemberDaoService memberDaoService;
    private final WalletDaoService walletDaoService;

    public WalletEntity single(Long id) {
        return walletDaoService.single(id);
    }

}

package com.last.lastcoin.service;

import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.dto.Member;
import com.last.lastcoin.dto.Member.MemberWalletUpdateRequest;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.enums.WalletType;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.service.dao.MemberDaoService;
import com.last.lastcoin.service.dao.WalletDaoService;
import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService extends BaseService {

    private final MemberDaoService memberDaoService;
    private final WalletDaoService walletDaoService;

    public MemberEntity findByWalletAddress(String address) {
        WalletEntity walletEntity = walletDaoService.findWalletByAddress(address);
        MemberEntity memberEntity = memberDaoService.single(walletEntity.getMemberId());
        return memberEntity;
    }

    public MemberEntity updateMemberType(Long id, Member.MemberUpdateRequest request) {
        MemberEntity memberEntity = memberDaoService.single(id);
        memberEntity.setType(request.getType());
        return memberDaoService.saveMember(memberEntity);
    }

    public Page<MemberEntity> list(Pages.PageRequest pageRequest) {
        return memberDaoService.list(pageRequest);
    }

    public List<MemberEntity> allList() {
        return memberDaoService.allList();
    }

    public MemberEntity single(Long memberId) {
        return memberDaoService.single(memberId);
    }

    public MemberEntity delete(Long memberId) {
        return memberDaoService.delete(memberId);
    }

    public WalletEntity updateWalletFromPhone(MemberWalletUpdateRequest request) {
        MemberEntity memberEntity = memberDaoService.singleByPhone(request.getPhone());
        WalletEntity wallet = walletDaoService.single(memberEntity.getId());
        wallet.setBalance(request.getAmount());

        if(wallet.getType() == WalletType.NORMAL.getValue()) {
            throw new CustomResponseStatusException(ExceptionCode.CREATE_WALLET_FAIL_NON_LOCKUP, "");
        }

        return walletDaoService.save(wallet);
    }
}

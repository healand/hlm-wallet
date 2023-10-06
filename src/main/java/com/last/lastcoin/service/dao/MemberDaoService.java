package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.MemberRepository;
import com.last.lastcoin.service.BaseService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberDaoService extends BaseService {

    private final MemberRepository memberRepository;
    private final WalletDaoService walletDaoService;

    @Transactional
    public MemberEntity createMember(MemberEntity memberEntity) {
        if (checkDuplicatePhone(memberEntity.getPhone()))
            throw new CustomResponseStatusException(ExceptionCode.DUPLICATED_MEMBER, "");
        MemberEntity save = memberRepository.save(memberEntity);
        return save;
    }

    @Transactional
    public MemberEntity saveMember(MemberEntity memberEntity) {
        if (!checkDuplicatePhone(memberEntity.getPhone()))
            throw new CustomResponseStatusException(ExceptionCode.ACCOUNT_NOT_FOUND, "");
        MemberEntity save = memberRepository.save(memberEntity);
        return save;
    }

    public MemberEntity findByWalletAddress(String address) {
        WalletEntity walletEntity = walletDaoService.findWalletByAddress(address);
        MemberEntity memberEntity = single(walletEntity.getMemberId());

        return memberEntity;
    }

    public MemberEntity findByPhone(String phone) {
        return memberRepository.findByPhoneAndDeletedTimeIsNull(phone)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.ACCOUNT_NOT_FOUND, "");
                });
    }

    public MemberEntity findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.ACCOUNT_NOT_FOUND, "");
                });
    }

    public Boolean checkDuplicatePhone(String phone) {
        return memberRepository.findByPhoneAndDeletedTimeIsNull(phone).isPresent();
    }

    public Page<MemberEntity> list(Pages.PageRequest pageRequest) {
        Page<MemberEntity> list;
        if (null == pageRequest.getKeyword() || pageRequest.getKeyword().equals("")) {
            list = memberRepository.findAllByDeletedTimeNull(generatePageable(pageRequest));
        } else {
            if (pageRequest.getKeywordType().toLowerCase().equals("name")) {
                list = memberRepository.findAllByNameContaining(pageRequest.getKeyword(),
                        generatePageable(pageRequest));
            } else {
                // } else if (pageRequest.getKeywordType().toLowerCase().equals("phone")) {
                list = memberRepository.findAllByPhoneContaining(pageRequest.getKeyword(),
                        generatePageable(pageRequest));
            }
        }
        return list;
    }

    public List<MemberEntity> allList(){
        List<MemberEntity> list;
        list = memberRepository.findAll();
        return list;
    }

    public MemberEntity single(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.ACCOUNT_NOT_FOUND, "");
                });
    }

    public MemberEntity singleByPhone(String phone) {
        return memberRepository.findByPhone(phone)
            .orElseThrow(() -> {
                throw new CustomResponseStatusException(ExceptionCode.ACCOUNT_NOT_FOUND, "");
            });
    }

    public MemberEntity delete(Long memberId) {
        MemberEntity entity = single(memberId);
        entity.setDeletedTime(LocalDateTime.now(ZoneOffset.UTC));
        return saveMember(entity);
    }

    public List<MemberEntity> all() {
        return memberRepository.findAllByStandardIsNull();
    }

    public MemberEntity findMaster() {
        return memberRepository.findAllByType(1)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.MASTER_NOT_FOUND, "");
                });
    }
}

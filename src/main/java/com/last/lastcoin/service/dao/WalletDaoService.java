package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.enums.WalletType;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.WalletRepository;
import com.last.lastcoin.service.BaseService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletDaoService extends BaseService {

    private final WalletRepository walletRepository;

    @Transactional
    public WalletEntity save(WalletEntity walletEntity) {
        return walletRepository.save(walletEntity);
    }

    @Transactional
    public WalletEntity createWallet(MemberEntity memberEntity, String symbol, WalletType type) {
        if (checkDuplicateWallet(memberEntity.getId(), type, symbol))
            throw new CustomResponseStatusException(ExceptionCode.WALLET_CREATE_FAIL, "");

        String uniqueId = "";
        String uniqueId2 = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
        Calendar dateTime = Calendar.getInstance();
        uniqueId = sdf.format(dateTime.getTime());
        uniqueId2 = sdf2.format(dateTime.getTime());
        String ran = RandomStringUtils.randomAlphanumeric(10);
        UUID uuidOne = UUID.randomUUID();
        //yyyymmddhh24missSSS_랜덤문자6개

        uniqueId = ran.substring(0,3) + uuidOne.toString().substring(0,7) + ran.substring(3,4) + uuidOne.toString().substring(9,13) + ran.substring(4,5);
        System.out.println(uniqueId);
//                uniqueId+"_"+ RandomStringUtils.randomAlphanumeric(6);

        String address = uniqueId;

        final boolean isNormalWallet = (type.getValue() == WalletType.NORMAL.getValue());
        final WalletEntity entity = WalletEntity.builder()
                .type(type.getValue())
                .memberId(memberEntity.getId())
                .name(isNormalWallet ? symbol + " 지갑" : symbol + " 락업지갑")
                .symbol(symbol)
                .balance(BigDecimal.valueOf(isNormalWallet ? 0 : memberEntity.getStandard()))
                .address(address)
                .build();

        return save(entity);
    }

    public Boolean checkDuplicateWallet(Long memberId, WalletType type, String symbol) {
        return walletRepository.findByMemberIdAndTypeAndSymbol(memberId, type.getValue(), symbol).isPresent();
    }
    public WalletEntity getWallet(List<WalletEntity> wallets, boolean isLockup) {
        return wallets.stream().filter(w -> w.getType() == (isLockup ? 1 : 0)).findFirst()
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
                });
    }

    public List<WalletEntity> getWalletList(HttpServletRequest request) {
        // REFACTOR: move request to controller
        // MemberEntity memberEntity =
        // memberService.findByPhone(self(request).getPhone());
        // return walletRepository.findByMemberId(memberEntity.getId()).orElseThrow(()
        // -> {
        // throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        // });

        return null;
    }

    public WalletEntity findWalletByAddress(String address) {
        System.out.println(address);
        if (null == address || address.length() < 0)
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        System.out.println(address);
        System.out.println(WalletType.LOCKUP.getValue());
        return walletRepository.findByAddressAndType(address, WalletType.LOCKUP.getValue()).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public List<WalletEntity> getWalletList(Long memberId) {
        return walletRepository.findByMemberId(memberId).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public WalletEntity findWallet(Long memberId, WalletType type, String symbol) {
        return walletRepository.findByMemberIdAndTypeAndSymbol(memberId, type.getValue(), symbol).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public WalletEntity single(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public WalletEntity findByWalletAddress(String address) {
        return walletRepository.findByAddress(address).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public List<WalletEntity> singleByMemberId(Long memberId) {
        return walletRepository.findByMemberId(memberId).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public WalletEntity findMasterWallet() {
        return walletRepository.findMasterWallet().orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        });
    }

    public BigDecimal getTotalWalletBalance() {
        return walletRepository.getTotalBalance().orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.INTERNAL_ERROR, "");
        });
    }

    public List<WalletEntity> getAllWallet() {
        return walletRepository.findAll();

        // REFACTOR: move request to controller
        // MemberEntity memberEntity =
        // memberService.findByPhone(self(request).getPhone());
        // return walletRepository.findByMemberId(memberEntity.getId()).orElseThrow(()
        // -> {
        // throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
        // });
    }
}

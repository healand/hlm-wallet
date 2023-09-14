package com.last.lastcoin.service;

import com.last.lastcoin.domain.HistoryEntity;
import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.domain.TransferEntity;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.dto.Transfer;
import com.last.lastcoin.enums.HistoryType;
import com.last.lastcoin.enums.SymbolType;
import com.last.lastcoin.enums.TransferType;
import com.last.lastcoin.enums.WalletType;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.service.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.MathContext;

@Service
@RequiredArgsConstructor
public class TransferService extends BaseService{

    private final MemberDaoService memberDaoService;
    private final WalletDaoService walletDaoService;
    private final TransferDaoService transferDaoService;
    private final HistoryDaoService historyDaoService;
    private final KeyValueDaoService keyValueDaoService;

    @Transactional
    public TransferEntity transferWithdraw(HttpServletRequest request, Transfer.TransferRequest transferRequest) {
        MemberEntity fromMemberEntity = memberDaoService.single(self(request).getId());
        WalletEntity fromWalletEntity = walletDaoService.findWallet(fromMemberEntity.getId(), WalletType.LOCKUP, SymbolType.CHICA);
        System.out.println(fromWalletEntity.getAddress());
        TransferType transferType = TransferType.TRANSFER_POINT;

        //최소소량 필요한가?
        if(transferRequest.getAmount().compareTo(BigDecimal.valueOf(1d)) < 0) {
            throw new CustomResponseStatusException(ExceptionCode.TRANSFER_INVALID_AMOUNT, "");
        }

        BigDecimal transactionFee = getGas();
        BigDecimal transferAmount = transferRequest.getAmount();
        // 잔액체크
        if (fromWalletEntity.getBalance().compareTo(transferAmount) < 0) {
            throw new CustomResponseStatusException(ExceptionCode.NOT_ENOUGH_BALANCE, "");
        }
        // !! 송금 금액이 수수료 이하만큼 부족하면 보낸 금액에서 제외
        if(fromWalletEntity.getBalance().compareTo(transferAmount.add(transactionFee)) < 0 ) {
            transferAmount = fromWalletEntity.getBalance().subtract(transactionFee).round(new MathContext(17));
            if(transferAmount.compareTo(BigDecimal.ZERO) <= 0)
                throw new CustomResponseStatusException(ExceptionCode.NOT_ENOUGH_BALANCE, "");
        }
        WalletEntity toWalletEntity = walletDaoService.findWalletByAddress(transferRequest.getReceiverAddress());
        System.out.println("!@#!@#!@#!@#!@#");
        System.out.println(toWalletEntity);
        TransferEntity transferEntity = TransferEntity.builder()
                .amount(transferAmount)
                .type(transferType.getValue())
                .description(transferRequest.getDescription())
                .status( 1 )
                .fromWalletAddress(fromWalletEntity.getAddress())
                .fromMemberId(fromMemberEntity.getId())
                .fromWalletId(fromWalletEntity.getId())
                .fromAfterBalance(fromWalletEntity.getBalance().subtract(transferAmount.add(transactionFee)))
                .toMemberId(toWalletEntity != null ? toWalletEntity.getMemberId() : null)
                .toWalletId(toWalletEntity != null ? toWalletEntity.getId() : null)
                .toWalletAddress(transferRequest.getReceiverAddress())
                .toAfterBalance(toWalletEntity != null ? toWalletEntity.getBalance().add(transferAmount) : null)
                .gasFee(BigDecimal.valueOf(0.0))
                .transferFee(transactionFee)
                .build();

        TransferEntity resultTransferEntity = transferDaoService.save(transferEntity);

        // 보낸지갑, 받는 지갑
        HistoryEntity fromWalletHistory = resultTransferEntity.getBaseHistoryEntity(HistoryType.HISTORY_WITHDRAW_INSIDE);
        fromWalletHistory.setOriginalBalance(fromWalletEntity.getBalance());
        fromWalletHistory.setAfterBalance(fromWalletEntity.getBalance().subtract(transferAmount.add(transactionFee)));
        historyDaoService.create(fromWalletHistory);

        HistoryEntity toWalletHistory = resultTransferEntity.getBaseHistoryEntity(HistoryType.HISTORY_DEPOSIT_INSIDE);
        toWalletHistory.setOriginalBalance(toWalletEntity.getBalance());
        toWalletHistory.setAfterBalance(toWalletEntity.getBalance().add(transferAmount));
        historyDaoService.create(toWalletHistory);
        toWalletEntity.setBalance(toWalletEntity.getBalance().add(transferAmount));


        // 지갑의 잔액 수정
        fromWalletEntity.setBalance(fromWalletEntity.getBalance().subtract((transferAmount.add(transactionFee))));

        return resultTransferEntity;

    }

    public BigDecimal getGas() {
        BigDecimal dec = new BigDecimal(keyValueDaoService.getKeyValue("gasFee").getValue());
        return dec;
    }
}

package com.last.lastcoin.service;

import com.last.lastcoin.domain.AdminLogEntity;
import com.last.lastcoin.domain.HistoryEntity;
import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.dto.History;
import com.last.lastcoin.dto.History.AdminInsertHistoryRequest;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.enums.HistoryType;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.service.dao.AdminLogDaoService;
import com.last.lastcoin.service.dao.HistoryDaoService;
import com.last.lastcoin.service.dao.MemberDaoService;
import com.last.lastcoin.service.dao.WalletDaoService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryService extends BaseService {

    private final HistoryDaoService historyDaoService;
    private final WalletDaoService walletDaoService;
    private final AdminLogDaoService adminLogDaoService;
    private final MemberDaoService memberDaoService;

    public Page<HistoryEntity> listAll(Pages.PageRequest pageRequest) {
        Page<HistoryEntity> res = historyDaoService.listAll(pageRequest);
        for(int i=0; i<res.getContent().size(); i++){
            MemberEntity member = memberDaoService.findById(res.getContent().get(i).getMemberId());
            res.getContent().get(i).setMember(member);
        }
        return historyDaoService.listAll(pageRequest);
    }

    public Page<HistoryEntity> listDate(Pages.PageRequest pageRequest, String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime st = LocalDate.parse(startDate,formatter).atStartOfDay();
        LocalDateTime et = LocalDate.parse(endDate,formatter).atStartOfDay();

        return historyDaoService.listDate(pageRequest, st,et);
    }

    public Page<HistoryEntity> listByWallet(Long walletId, Pages.PageRequest pageRequest) {
        return historyDaoService.listByWallet(walletId, pageRequest);
    }

    @Transactional
    public Integer insertCoinList(List<AdminInsertHistoryRequest> list) {
        int result = 0;
        for (AdminInsertHistoryRequest request : list) {
            String type = HistoryType.HISTORY_DEPOSIT_ADMIN.getValue();
            if (request.getAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
                type = HistoryType.HISTORY_WITHDRAW_ADMIN.getValue();
            }

            WalletEntity walletEntity = walletDaoService.single(request.getWalletId());
            if (walletEntity == null) {
                throw new CustomResponseStatusException(ExceptionCode.WALLET_NOT_FOUND, "");
            }

            if (walletEntity.getBalance().add(request.getAmount()).compareTo(BigDecimal.ZERO)  < 0) {
                throw new CustomResponseStatusException(ExceptionCode.WALLET_CANT_MINUS, "");
            }

            HistoryEntity entity = historyDaoService.save(HistoryEntity.builder()
                .walletId(request.getWalletId())
                .memberId(walletEntity.getMemberId())
                .status(1)
                .gasFee(BigDecimal.valueOf(0))
                .transferFee(BigDecimal.valueOf(0))
                .type(type)
                .amount(request.getAmount())
                .originalBalance(walletEntity.getBalance())
                .afterBalance(walletEntity.getBalance().add(request.getAmount()))
                .description(request.getDescription()).build());

            walletEntity.setBalance(walletEntity.getBalance().add(request.getAmount()));

            AdminLogEntity adminLogEntity = new AdminLogEntity();
            adminLogEntity.setAdminId(request.getAdminId());
            adminLogEntity.setIp(request.getIp());
            adminLogEntity.setDescription(request.getLogDescription() + "(" + request.getAmount() + "coin)");
            adminLogEntity.setTargetId(request.getWalletId());
            adminLogEntity.setType("wallet");

            adminLogDaoService.insertAdminLog(adminLogEntity);

            if (entity.getId() > 0) {
                result++;
            }
        }
        return result;
    }
}

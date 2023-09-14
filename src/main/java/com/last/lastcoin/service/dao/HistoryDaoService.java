package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.HistoryEntity;
import com.last.lastcoin.dto.History;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.repository.HistoryRepository;
import com.last.lastcoin.service.BaseService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryDaoService extends BaseService {

    private final HistoryRepository historyRepository;

    @Transactional
    public HistoryEntity create(HistoryEntity entity) {
        return historyRepository.save(entity);
    }

    public Page<HistoryEntity> listAll(Pages.PageRequest pageRequest) {
        return historyRepository.findAllBy(generatePageable(pageRequest));
    }

    public Page<HistoryEntity> listDate(Pages.PageRequest pageRequest, LocalDateTime startDate, LocalDateTime endDate){
        return historyRepository.findByCreatedTimeBetween(generatePageable(pageRequest),startDate, endDate);
    }

    public Page<HistoryEntity> listByWallet(Long walletId, Pages.PageRequest pageRequest) {
        return historyRepository.findByWalletId(walletId, generatePageable(pageRequest));
    }

    public Page<HistoryEntity> listByMember(Long memberId, Pages.PageRequest pageRequest) {
        return historyRepository.findByMemberId(memberId, generatePageable(pageRequest));
    }

    public HistoryEntity save(HistoryEntity historyEntity) {
        return historyRepository.save(historyEntity);
    }

}

package com.last.lastcoin.repository;

import com.last.lastcoin.domain.HistoryEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    Page<HistoryEntity> findByWalletId(Long walletId, Pageable pageRequest);
    Page<HistoryEntity> findByMemberId(Long memberId, Pageable pageRequest);

    Page<HistoryEntity> findAllBy(Pageable pageRequest);

    Page<HistoryEntity> findByCreatedTimeBetween(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);
}

package com.last.lastcoin.repository;

import com.last.lastcoin.domain.BannerEntity;
import com.last.lastcoin.domain.SchedulesEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<BannerEntity, Long> {
    Optional<List<BannerEntity>> findAllByDeletedTimeNullOrderBySequence();
    Optional<List<BannerEntity>> findAllByStatusAndDeletedTimeNullOrderBySequence(Integer status);
}
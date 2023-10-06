package com.last.lastcoin.repository;

import com.last.lastcoin.domain.SchedulesEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<SchedulesEntity, Long> {
    Optional<List<SchedulesEntity>> findAllByStatusAndEventTimeBetweenAndDeletedTimeNullOrderByEventTimeAsc(
            Integer status, LocalDateTime startDate, LocalDateTime endDate);

    Optional<List<SchedulesEntity>> findAllByEventTimeBetweenAndDeletedTimeNullOrderByEventTimeAsc(
        LocalDateTime startDate, LocalDateTime endDate);
}
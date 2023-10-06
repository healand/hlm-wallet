package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.AdminLogEntity;
import com.last.lastcoin.domain.SchedulesEntity;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.SchedulesRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulesDaoService {

    private final SchedulesRepository schedulesRepository;

    public List<SchedulesEntity> getScheduleList(Integer status, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new CustomResponseStatusException(ExceptionCode.BAD_REQUEST_DATE_ERROR, "");
        }
        if(status == -1)
            return schedulesRepository
                .findAllByEventTimeBetweenAndDeletedTimeNullOrderByEventTimeAsc(startDate, endDate)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.INTERNAL_ERROR, "");
                });
        else
            return schedulesRepository
                .findAllByStatusAndEventTimeBetweenAndDeletedTimeNullOrderByEventTimeAsc(status, startDate, endDate)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.INTERNAL_ERROR, "");
                });
    }

    public SchedulesEntity getSchedule(Long id) {
        return schedulesRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.INTERNAL_ERROR, "");
                });
    }

    public SchedulesEntity upsertSchedule(SchedulesEntity schedulesEntity) {
        SchedulesEntity save = schedulesRepository.save(schedulesEntity);
        return save;
    }
}

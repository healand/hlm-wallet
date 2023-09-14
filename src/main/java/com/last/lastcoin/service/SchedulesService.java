package com.last.lastcoin.service;

import com.last.lastcoin.domain.SchedulesEntity;
import com.last.lastcoin.service.dao.SchedulesDaoService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulesService {

    private final SchedulesDaoService schedulesDaoService;

    public List<SchedulesEntity> getScheduleList(Integer status, LocalDateTime startDate, LocalDateTime endDate) {
        return schedulesDaoService.getScheduleList(status, startDate, endDate);
    }

    public SchedulesEntity getSchedule(Long id) {
        return schedulesDaoService.getSchedule(id);
    }

    public SchedulesEntity upsertSchedule(SchedulesEntity schedulesEntity) {
        return schedulesDaoService.upsertSchedule(schedulesEntity);
    }
}

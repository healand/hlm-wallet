package com.last.lastcoin.service;

import com.last.lastcoin.domain.BannerEntity;
import com.last.lastcoin.service.dao.BannerDaoService;
import com.last.lastcoin.service.dao.SchedulesDaoService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerDaoService bannerDaoService;

    public BannerEntity single(Long id) {
        return bannerDaoService.single(id);
    }
    public List<BannerEntity> getBannerList(Integer status) {
        return bannerDaoService.getBannerList(status);
    }

    public BannerEntity upsertBanner(BannerEntity bannerEntity) {
        return bannerDaoService.upsertBanner(bannerEntity);
    }
}

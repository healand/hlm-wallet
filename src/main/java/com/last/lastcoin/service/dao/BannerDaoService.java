package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.BannerEntity;
import com.last.lastcoin.domain.SchedulesEntity;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.BannerRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerDaoService {

    private final BannerRepository bannerRepository;

    public BannerEntity single(Long id) {
        return bannerRepository.findById(id)
            .orElseThrow(() -> {
                throw new CustomResponseStatusException(ExceptionCode.BANNER_NOT_FOUND, "");
            });
    }

    public List<BannerEntity> getBannerList(Integer status) {
        if (status == -1) {
            return bannerRepository
                .findAllByDeletedTimeNullOrderBySequence()
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.BANNER_NOT_FOUND, "");
                });
        } else {
            return bannerRepository
                .findAllByStatusAndDeletedTimeNullOrderBySequence(status)
                .orElseThrow(() -> {
                    throw new CustomResponseStatusException(ExceptionCode.BANNER_NOT_FOUND, "");
                });
        }
    }

    public BannerEntity upsertBanner(BannerEntity bannerEntity) {
        BannerEntity save = bannerRepository.save(bannerEntity);
        return save;
    }
}

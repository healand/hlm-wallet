package com.last.lastcoin.service.dao;

import com.last.lastcoin.domain.NoticeEntity;
import com.last.lastcoin.dto.Notice;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.exception.CustomResponseStatusException;
import com.last.lastcoin.exception.ExceptionCode;
import com.last.lastcoin.repository.NoticeRepository;
import com.last.lastcoin.service.BaseService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeDaoService extends BaseService {

    private final NoticeRepository noticeRepository;

    public Page<NoticeEntity> findList(Integer status, Pages.PageRequest pageRequest) {
        Page<NoticeEntity> list;
        if (status == -1) {
            if (null == pageRequest.getKeyword() || pageRequest.getKeyword().equals("")) {
                list = noticeRepository.findAllByDeletedTimeNullOrderByCreatedTime(generatePageable(pageRequest));
            } else {
                list = noticeRepository.findAllByDeletedTimeNullAndTitleContainingOrderByCreatedTime(
                    pageRequest.getKeyword(),
                    generatePageable(pageRequest));
            }
        } else {
            list = noticeRepository.findByStatusAndDeletedTimeNullOrderByCreatedTime(status,
                generatePageable(pageRequest));
        }
        return list;
    }

    public NoticeEntity findById(Long id) {
        NoticeEntity noticeEntity = noticeRepository.findByIdAndDeletedTimeNull(id)
            .orElseThrow(() -> {
                throw new CustomResponseStatusException(ExceptionCode.NOTICE_NOT_FOUND, "");
            });

        return noticeEntity;
    }

    @Transactional
    public NoticeEntity create(NoticeEntity noticeEntity) {
        NoticeEntity save = noticeRepository.save(noticeEntity);
        return save;
    }

    @Transactional
    public NoticeEntity update(Long id, Notice.NoticeRequest notice) {
        NoticeEntity entity = noticeRepository.findById(id).orElseThrow(() -> {
            throw new CustomResponseStatusException(ExceptionCode.NOTICE_NOT_FOUND, "");
        });
        entity.setStatus(notice.getStatus());
        entity.setTitle(notice.getTitle());
        entity.setContent(notice.getContent());
        NoticeEntity save = noticeRepository.save(entity);
        return save;
    }

    @Transactional
    public NoticeEntity delete(Long id) {
        try {
            NoticeEntity entity = findById(id);
            entity.setDeletedTime(LocalDateTime.now(ZoneOffset.UTC));
            NoticeEntity save = noticeRepository.save(entity);
            return save;
        } catch (Exception e) {
            throw e;
        }
    }
}

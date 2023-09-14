package com.last.lastcoin.service;

import com.last.lastcoin.domain.NoticeEntity;
import com.last.lastcoin.dto.Notice;
import com.last.lastcoin.dto.base.Pages;
import com.last.lastcoin.service.dao.NoticeDaoService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService extends BaseService {

    private final NoticeDaoService noticeDaoService;

    public Page<NoticeEntity> findList(Integer status, Pages.PageRequest pageRequest) {
        return noticeDaoService.findList(status, pageRequest);
    }

    public NoticeEntity findById(Long id) {
        return noticeDaoService.findById(id);
    }

    @Transactional
    public NoticeEntity create(NoticeEntity noticeEntity) {
        return noticeDaoService.create(noticeEntity);
    }

    @Transactional
    public NoticeEntity update(Long id, Notice.NoticeRequest notice) {
        return noticeDaoService.update(id, notice);
    }

    @Transactional
    public NoticeEntity delete(Long id) {
        return noticeDaoService.delete(id);
    }
}

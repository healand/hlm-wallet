package com.last.lastcoin.repository;

import com.last.lastcoin.domain.NoticeEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    Page<NoticeEntity> findByStatusAndDeletedTimeNullOrderByCreatedTime(Integer status, Pageable pageable);

    Page<NoticeEntity> findAllByDeletedTimeNullOrderByCreatedTime(Pageable pageable);

    Page<NoticeEntity> findAllByDeletedTimeNullAndTitleContainingOrderByCreatedTime(String title, Pageable pageable);

    Optional<NoticeEntity> findByIdAndDeletedTimeNull(Long id);
}

package com.last.lastcoin.repository;

import com.last.lastcoin.domain.AdminLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLogEntity,Long> {

    Page<AdminLogEntity> findAllByAdminId(Long id, Pageable pageable);
}

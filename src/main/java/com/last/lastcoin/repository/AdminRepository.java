package com.last.lastcoin.repository;

import com.last.lastcoin.domain.AdminEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByNameAndDeletedTimeIsNull(String name);

    Page<AdminEntity> findAllByDeletedTimeNull(Pageable pageable);

    Page<AdminEntity> findAllByNameContaining(String name, Pageable pageable);
}

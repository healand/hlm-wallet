package com.last.lastcoin.repository;

import com.last.lastcoin.domain.StandardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRepository extends JpaRepository<StandardEntity, String> {
    StandardEntity findByPhone(String phone);
}

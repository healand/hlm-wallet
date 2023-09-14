package com.last.lastcoin.repository;

import com.last.lastcoin.domain.AuthCodeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCodeEntity, Long> {
    @Query("select a from AuthCodeEntity a where a.phone = :phone and a.status = :status order by a.createdTime desc")
    List<AuthCodeEntity> findAllByStatusAndPhone(Integer status, String phone);
}

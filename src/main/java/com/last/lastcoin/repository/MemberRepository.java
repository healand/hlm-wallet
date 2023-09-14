package com.last.lastcoin.repository;

import com.last.lastcoin.domain.MemberEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByPhoneAndDeletedTimeIsNull(String phone);

    Optional<MemberEntity> findById(Long id);

    Optional<MemberEntity> findByPhone(String phone);

    Optional<MemberEntity> findAllByType(Integer type);

    Page<MemberEntity> findAllByDeletedTimeNull(Pageable pageable);

    Page<MemberEntity> findAllByNameContaining(String name, Pageable pageable);

    Page<MemberEntity> findAllByPhoneContaining(String phone, Pageable pageable);

    Optional<MemberEntity> findByPhoneAndPinAndDeletedTimeIsNull(String phone, String pin);

    List<MemberEntity> findAllByStandardIsNull();
}

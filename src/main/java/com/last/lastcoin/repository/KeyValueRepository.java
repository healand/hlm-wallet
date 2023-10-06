package com.last.lastcoin.repository;

import com.last.lastcoin.domain.KeyValueEntity;
import com.last.lastcoin.enums.KeyValue;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeyValueRepository extends JpaRepository<KeyValueEntity, String> {
    Optional<KeyValueEntity> findKeyValueEntityByKey(String key);

    @Query(value = "update keyvalue k set `value` = ?2 where `key` = ?1 ", nativeQuery = true)
    @Modifying
    Integer update(String key, String value);

    @Query(nativeQuery = true, value = "SELECT * FROM keyvalue as k WHERE k.key IN (:keys)")
    List<KeyValueEntity> findKeyValueList(@Param("keys") List<String> keys);
}

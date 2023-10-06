package com.last.lastcoin.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // @CreatedDate
    private LocalDateTime createdTime;

    // @LastModifiedDate
    private LocalDateTime updatedTime;

    @PrePersist
    public void onPrePersist() {
        this.createdTime = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedTime = this.createdTime;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedTime = LocalDateTime.now(ZoneOffset.UTC);
    }
}

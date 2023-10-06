package com.last.lastcoin.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.domain.AdminEntity;
import com.last.lastcoin.domain.BaseTimeEntity;
import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AdminLog {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminLogResponse {
        private Long id;
        private Long adminId;
        private String ip;
        private String description;
        private Long targetId;
        private String type;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime createdTime;

        AdminEntity admin;

        WalletEntity wallet;

    }
}

package com.last.lastcoin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.domain.MemberEntity;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class History {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminInsertHistoryRequest {
        @ApiModelProperty(example = "")
        private Long walletId;
        @ApiModelProperty(example = "100")
        private BigDecimal amount;
        @ApiModelProperty(example = "메모")
        private String description;
        private Long adminId;
        private String ip;
        private String logDescription;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryResponse {
        private Long id;
        private String type;
        @Column(columnDefinition = "TEXT")
        private String counterAddress;
        private String status;
        private BigDecimal amount;
        private BigDecimal transferFee;
        private BigDecimal originalBalance;
        private String description;
        @Column(columnDefinition = "TEXT")
        private String txId;
        private BigDecimal afterBalance;
        private Long memberId;

        MemberEntity member;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime createdTime;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime updatedTime;
    }

    public interface HistoryResponseInterface {
        Long getId();
        String getName();
        String getTitle();
        BigDecimal getAmount();
        String getCounterAddress();
        String getPhone();
        String getDescription();
        String getTxId();
        String getCreatedTime();
    }
}

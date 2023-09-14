package com.last.lastcoin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.domain.WalletEntity;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Member {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MemberCommonResponse {
        private String message;
        private Boolean result;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MemberRequest {
        private String email;
        private String password;
        private String pin;
        private String phone;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MemberUpdateRequest {
        private String name;
        private String phone;
        private int type;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MemberResponse {
        private Long id;
        private Integer type;
        private String name;
        private String phone;
        private Long standard;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime createdTime;
        private Integer status;

        List<Wallet.WalletResponse> walletList;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MemberWalletUpdateRequest {
        private String phone;
        private BigDecimal amount;
    }
}

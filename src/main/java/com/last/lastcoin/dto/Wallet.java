package com.last.lastcoin.dto;

import java.math.BigDecimal;
import javax.persistence.Column;

import com.last.lastcoin.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Wallet {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WalletResponse {
        private Long id;
        private Integer type;
        private String name;
        private String symbol;
        @Column(columnDefinition = "TEXT")
        private String address;
        private BigDecimal balance;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WalletMemberResponse {
        private Long id;
        private Integer type;
        private String name;
        private String symbol;
        @Column(columnDefinition = "TEXT")
        private String address;
        private BigDecimal balance;
        private MemberEntity member;
    }
}

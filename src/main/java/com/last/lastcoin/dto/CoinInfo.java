package com.last.lastcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CoinInfo {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoinInfoResponse {
        private String symbol;
        private Double coinToWon;
        private Double changeRate;
    }
}

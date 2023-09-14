package com.last.lastcoin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class Transfer {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransferRequest {
        @ApiModelProperty(example = "10")
        private BigDecimal amount;
        @ApiModelProperty(example = "")
        private String receiverAddress;
        @ApiModelProperty(example = "메모")
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransferResponse {
        private String type;
        private BigDecimal amount;
        private BigDecimal transferFee;
        private String toWalletAddress;
        private String txId;
    }
}

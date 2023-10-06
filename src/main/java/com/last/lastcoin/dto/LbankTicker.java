package com.last.lastcoin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class LbankTicker {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModelResponse {

        private String symbol;
        private String timestamp;

        private TickerResponse ticker;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TickerResponse {

        private Double vol;
        private Double high;
        private Double low;
        private Double change;
        private Double turnover;
        private Double latest;
    }

}

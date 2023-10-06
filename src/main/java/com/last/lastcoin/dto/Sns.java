package com.last.lastcoin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Sns {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SnsResponse {
        private String twitter;
        private String facebook;
        private String medium;
        private String instagram;
        private String discord;
        private String tiktok;
        private String youtube;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsertSnsRequest {
        @ApiModelProperty(example = "")
        private String name;
        @ApiModelProperty(example = "")
        private String link;
        @ApiModelProperty(example = "")
        private Long adminId;
        @ApiModelProperty(example = "")
        private String ip;
    }
}

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

public class Banner {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsertBannerRequest {
        @ApiModelProperty(example = "https://d3kqdc25i4tl0t.cloudfront.net/articles/content/fbcd33859f5566908eabadc6cfb27228_hero.jpeg")
        private String image;
        @ApiModelProperty(example = "www.naver.com")
        private String url;
        @ApiModelProperty(example = "1")
        private Integer status;
        @ApiModelProperty(example = "99")
        private Integer sequence;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BannerResponse {
        private Long id;
        private String url;
        private String image;
        private Integer sequence;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime createdTime;
    }
}

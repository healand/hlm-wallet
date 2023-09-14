package com.last.lastcoin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

public class Notice {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoticeResponse {
        private Long id;
        private String title;
        private String content;
        private Integer status;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime createdTime;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime updatedTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoticeListResponse {
        private Long id;
        private String title;
        private String content;
        private Integer status;

        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoticeRequest {
        @ApiModelProperty(example = "title")
        private String title;
        @ApiModelProperty(example = "content")
        private String content;
        @ApiModelProperty(example = "1")
        private Integer status;
        @ApiModelProperty(example = "1")
        private Long adminId;
        private String ip;
    }
}

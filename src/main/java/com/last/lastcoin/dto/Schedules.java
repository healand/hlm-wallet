package com.last.lastcoin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

public class Schedules {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SchedulesRequest {

        @ApiModelProperty(example = "2023-01-01 00:00:00", position = 1)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startDate;
        @ApiModelProperty(example = "2023-12-31 23:59:59", position = 2)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endDate;
        @ApiModelProperty(example = "1")
        private Integer status;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsertRequest {
        @ApiModelProperty(example = "testTitle")
        private String title;
        @ApiModelProperty(example = "testDescription")
        private String description;
        @ApiModelProperty(example = "1")
        private Integer status;
        @ApiModelProperty(example = "1")
        private Integer timeType;
        @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
        @ApiModelProperty(example = "2023-01-01T00:00:00")
        private LocalDateTime eventTime;
        @ApiModelProperty(example = "1")
        private Long adminId;
        private String ip;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SchedulesResponse {
        private Long id;
        private String title;
        private String description;
        private String image;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime eventTime;
        private Integer timeType;
        private Integer status;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SchedulesListResponse {
        private Long id;
        private String title;
        private String image;
        @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
        private LocalDateTime eventTime;
        private Integer timeType;
        private Integer status;
        private String description;
    }

}

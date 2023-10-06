package com.last.lastcoin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AppVersion {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppVersionResponse {
        @ApiModelProperty(notes = "iOS버전", position = 0)
        private String iosVersion;
        @ApiModelProperty(notes = "iOS 필수 업데이트 여부", position = 1)
        private Integer iosRequire; // 0 = 필수, 1= 필수 x
        @ApiModelProperty(notes = "iOS 다운로드 링크")
        private String iosUrl;
        @ApiModelProperty(notes = "AOS버전", position = 2)
        private String aosVersion;
        @ApiModelProperty(notes = "AOS 필수 업데이트 여부", position = 3)
        private Integer aosRequire; // 0 = 필수, 1= 필수 x
        @ApiModelProperty(notes = "AOS 다운로드 링크")
        private String aosUrl;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class AppVersionRequest {
        private String iosVersion;
        private Integer iosRequire; // 0 = 필수, 1= 필수 x
        private String iosUrl;
        private String aosVersion;
        private Integer aosRequire; // 0 = 필수, 1= 필수 x
        private String aosUrl;
    }
}
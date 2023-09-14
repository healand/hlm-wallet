package com.last.lastcoin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Auth {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpRequest {
        @ApiModelProperty(example = "tester")
        private String name;
        private String phone;
        @ApiModelProperty(example = "")
        private String fcmToken;
        @ApiModelProperty(example = "91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203")
        private String pin;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminSignUpRequest {
        @ApiModelProperty(example = "tester")
        private String name;
        @ApiModelProperty(example = "")
        private String password;
        @ApiModelProperty(example = "")
        private Integer type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminSignInRequest {
        private String name;
        private String password;
        private String ip;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInRequest {
        private String phone;
        @ApiModelProperty(example = "91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203")
        private String pin;
        private String fcmToken;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PinUpdateRequest {
        private String phone;
        @ApiModelProperty(example = "91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203")
        private String pin;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PinUpdateResponse {
        private Boolean result;
        private String message;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private Boolean result;
        private String token;
        private String message;
        private Long id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SmsRequest {
        private String apiKey;
        private String phone;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SmsVerifyRequest {
        private String phone;
        @ApiModelProperty(example = "000000")
        private String code;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SmsAdminVerifyRequest {
        private String phone;
        @ApiModelProperty(example = "000000")
        private String code;
        private String ip;
        private Long adminId;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InitRequest {
        private String initPassword;
    }
}

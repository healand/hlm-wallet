package com.last.lastcoin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Contract {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContractResponse {

        @ApiModelProperty(notes = "서비스 이용약관 링크")
        private String serviceContractUrl;
        @ApiModelProperty(notes = "개인정보 처리방침 링크")
        private String personalContractUrl;
    }
}

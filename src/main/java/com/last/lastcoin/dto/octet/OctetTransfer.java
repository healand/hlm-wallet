package com.last.lastcoin.dto.octet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OctetTransfer {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransferRequest {
        private String symbol;
        private String contractAddress;
        private String requestId;
        private String amount;
        private String senderAddress;
        private String receiverAddress;
        private String encryptedUserKey;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        @JsonProperty("uuid")
        private String uuid;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetransferRequest {

        private String txid;
        private String reqId;
        private String gasPrice;
        private Integer passphrase;
        private String privateKey;
    }

}

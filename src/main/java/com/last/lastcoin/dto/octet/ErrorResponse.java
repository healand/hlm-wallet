package com.last.lastcoin.dto.octet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String errorCode;
}

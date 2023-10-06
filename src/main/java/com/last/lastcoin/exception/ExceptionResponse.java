package com.last.lastcoin.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.last.lastcoin.utils.serializer.UtcZoneDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ExceptionResponse {

    private final int code;
    private final boolean result;
    private final String codeName;
    private final String message;
    @JsonSerialize(using = UtcZoneDateTimeSerializer.class)
    private final LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);

    public static ResponseEntity<ExceptionResponse> toResponseEntity(ExceptionCode errorCode, String message) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ExceptionResponse.builder()
                        .result(false)
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage() + message)
                        .codeName(errorCode.name())
                        .build());
    }

}

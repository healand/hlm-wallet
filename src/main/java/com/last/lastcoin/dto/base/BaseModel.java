package com.last.lastcoin.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BaseModel {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Common {
        private Boolean result;
    }
}

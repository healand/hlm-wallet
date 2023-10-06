package com.last.lastcoin.dto.base;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Pages {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageRequest {
        @ApiParam(value = "페이지 넘버 - 1페이지가 처음", required = true, example = "1")
        Integer current;
        @ApiParam(value = "1페이지 당 개수", required = true, example = "10")
        Integer pageSize;
        @ApiParam(value = "검색 필드", required = false, example = "")
        String keywordType;
        @ApiParam(value = "검색 키워드", required = false, example = "")
        String keyword;
    }
}

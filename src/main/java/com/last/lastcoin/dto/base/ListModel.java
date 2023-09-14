package com.last.lastcoin.dto.base;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ListModel<T> {
    // DB상 전체 카운트 개수(반환 개수 X)
    public int totalCount;

    public T data;

    @Builder
    public ListModel(int totalCount, T data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public ListModel(Long totalCount, T data) {
        this.totalCount = totalCount.intValue();
        this.data = data;
    }
}

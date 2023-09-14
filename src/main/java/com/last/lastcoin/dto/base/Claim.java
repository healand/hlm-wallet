package com.last.lastcoin.dto.base;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Claim {
    private Long id;
    private String name;
    private String phone;
}

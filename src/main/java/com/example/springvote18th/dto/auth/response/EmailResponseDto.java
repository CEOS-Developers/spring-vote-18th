package com.example.springvote18th.dto.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailResponseDto {
    private String code;
}

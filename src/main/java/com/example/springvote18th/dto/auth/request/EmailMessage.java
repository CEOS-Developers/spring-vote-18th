package com.example.springvote18th.dto.auth.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailMessage {
    private String to;
    private String subject;
}

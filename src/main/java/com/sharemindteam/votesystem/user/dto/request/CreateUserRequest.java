package com.sharemindteam.votesystem.user.dto.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String loginId;
    private String name;
    private String email;
    private String password;
    private String partName;
    private String teamName;
}

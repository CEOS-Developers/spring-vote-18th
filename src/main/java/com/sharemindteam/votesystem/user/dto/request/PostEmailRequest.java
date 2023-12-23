package com.sharemindteam.votesystem.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostEmailRequest {
    @NotNull
    @NotBlank
    @Email
    private String email;
}

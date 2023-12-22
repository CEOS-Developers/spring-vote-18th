package com.sharemindteam.votesystem.email.presentation;

import com.sharemindteam.votesystem.email.application.EmailService;
import com.sharemindteam.votesystem.email.dto.request.PostCodeRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "email Controller", description = "이메일 컨트롤러")
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> verifyCode(@RequestBody PostCodeRequest postCodeRequest) {
        emailService.verifyCode(postCodeRequest.getEmail(), postCodeRequest.getCode());
        return ResponseEntity.ok().build();
    }
}

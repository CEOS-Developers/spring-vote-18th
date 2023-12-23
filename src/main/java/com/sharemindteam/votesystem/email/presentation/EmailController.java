package com.sharemindteam.votesystem.email.presentation;

import com.sharemindteam.votesystem.email.application.EmailService;
import com.sharemindteam.votesystem.email.dto.request.PostCodeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "Email Controller", description = "이메일 컨트롤러")
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @Operation(summary = "이메일 코드 체크", description = "이메일 인증 코드가 맞는지 체크합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "이메일 인증 코드 일치"),
            @ApiResponse(responseCode = "400", description = "이메일 코드 불일치")
    })
    @PostMapping
    public ResponseEntity<Void> verifyCode(@RequestBody PostCodeRequest postCodeRequest) {
        emailService.verifyCode(postCodeRequest.getEmail(), postCodeRequest.getCode());
        return ResponseEntity.ok().build();
    }
}

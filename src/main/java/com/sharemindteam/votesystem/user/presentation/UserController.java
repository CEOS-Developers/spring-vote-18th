package com.sharemindteam.votesystem.user.presentation;

import com.sharemindteam.votesystem.email.application.EmailService;
import com.sharemindteam.votesystem.email.exception.InvalidEmailException;
import com.sharemindteam.votesystem.user.application.UserService;
import com.sharemindteam.votesystem.user.dto.request.PostEmailRequest;
import com.sharemindteam.votesystem.user.dto.request.PostLoginIdRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "User Controller", description = "유저 컨트롤러")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @GetMapping
    public String getHello() {
        return "hello";
    }

    @Operation(summary = "로그인 아이디 중복 체크", description = "해당 로그인 아이디 중복인지(사용가능한지) 체크합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용 가능한 로그인 아이디"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 로그인 아이디")
    })
    @PostMapping("/loginId")
    public ResponseEntity<Void> isDuplicatedLoginId(@RequestBody PostLoginIdRequest postLoginIdRequest) {
        userService.validateLoginId(postLoginIdRequest.getLoginId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 아이디 중복 체크 후 메일 전송", description = "이메일 중복 체크 && 해당 이메일로 메일 전송합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용 가능한 이메일 아이디 && 메일 전송 성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 이메일"),
            @ApiResponse(responseCode = "400", description = "이메일 형식이 올바르지 않음"),
            @ApiResponse(responseCode = "403", description = "유효기간(3분)이 지나지 않은 이메일 코드가 남아있음")
    })
    @PostMapping("/email")
    public ResponseEntity<Void> isDuplicatedEmail(@Valid @RequestBody PostEmailRequest postEmailRequest,
                                                  Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidEmailException(postEmailRequest.getEmail());
        }
        userService.validateEmail(postEmailRequest.getEmail());
        emailService.sendVerificationCode(postEmailRequest.getEmail());
        return ResponseEntity.ok().build();
    }
}

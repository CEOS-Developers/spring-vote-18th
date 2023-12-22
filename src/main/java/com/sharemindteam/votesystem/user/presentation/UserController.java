package com.sharemindteam.votesystem.user.presentation;

import com.sharemindteam.votesystem.email.exception.InvalidEmailException;
import com.sharemindteam.votesystem.user.application.UserService;
import com.sharemindteam.votesystem.user.dto.request.PostEmailRequest;
import com.sharemindteam.votesystem.user.dto.request.PostLoginIdRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Controller", description = "유저 컨트롤러")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping
    public String getHello() {
        return "hello";
    }

    @PostMapping("/loginId")
    public ResponseEntity<Void> isDuplicatedLoginId(@RequestBody PostLoginIdRequest postLoginIdRequest) {
        userService.validateLoginId(postLoginIdRequest.getLoginId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email")
    public ResponseEntity<Void> isDuplicatedEmail(@Valid @RequestBody PostEmailRequest postEmailRequest,
                                                  Errors errors) {
        System.out.println("error: "+errors);
        if (errors.hasErrors()) {
            throw new InvalidEmailException(postEmailRequest.getEmail());
        }
        userService.validateEmail(postEmailRequest.getEmail());
        return ResponseEntity.ok().build();
    }
}

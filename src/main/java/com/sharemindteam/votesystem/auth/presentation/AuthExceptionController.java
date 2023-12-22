package com.sharemindteam.votesystem.auth.presentation;

import com.sharemindteam.votesystem.auth.exception.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthExceptionController {
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> catchTokenExpiredException(TokenExpiredException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}

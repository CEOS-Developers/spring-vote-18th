package com.sharemindteam.votesystem.email.presentation;

import com.sharemindteam.votesystem.email.exception.InvalidEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class EmailExceptionController {
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> catchInvalidEmailException(InvalidEmailException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

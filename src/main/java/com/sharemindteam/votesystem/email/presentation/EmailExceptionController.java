package com.sharemindteam.votesystem.email.presentation;

import com.sharemindteam.votesystem.email.exception.CodeAlreadyExistsException;
import com.sharemindteam.votesystem.email.exception.InvalidCodeException;
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

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<String> catchInvalidCodeException(InvalidCodeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CodeAlreadyExistsException.class)
    public ResponseEntity<String> catchCodeAlreadyExistsException(CodeAlreadyExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}

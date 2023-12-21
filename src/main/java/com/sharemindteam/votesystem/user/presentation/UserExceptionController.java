package com.sharemindteam.votesystem.user.presentation;

import com.sharemindteam.votesystem.user.exception.EmailAlreadyExistsException;
import com.sharemindteam.votesystem.user.exception.LoginIdAlreadyExistsException;
import com.sharemindteam.votesystem.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> catchUserNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> catchEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(LoginIdAlreadyExistsException.class)
    public ResponseEntity<String> LoginIdAlreadyExistsException(LoginIdAlreadyExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}

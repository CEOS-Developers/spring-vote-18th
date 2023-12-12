package com.sharemindteam.votesystem.global.presentation;

import com.sharemindteam.votesystem.global.exception.AlreadyVotedException;
import com.sharemindteam.votesystem.global.exception.CandidateNotFoundException;
import com.sharemindteam.votesystem.global.exception.IllegalVoteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<String> catchAlreadyVotedException(AlreadyVotedException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> catchCandidateNotFoundException(CandidateNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalVoteException.class)
    public ResponseEntity<String> catchIllegalVoteException(IllegalVoteException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

package com.sharemindteam.votesystem.global.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(Long id) {
        super("해당하는 후보 정보를 찾을 수 없습니다. : " + id);
    }
}

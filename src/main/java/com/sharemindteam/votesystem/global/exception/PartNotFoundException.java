package com.sharemindteam.votesystem.global.exception;

public class PartNotFoundException extends RuntimeException {
    public PartNotFoundException(String wrongPart) {
        super("해당하는 파트가 존재하지 않습니다. : " + wrongPart);
    }
}

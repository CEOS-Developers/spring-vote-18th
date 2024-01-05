package com.sharemindteam.votesystem.global.exception;

public class AlreadyVotedException extends RuntimeException {
    public AlreadyVotedException() {
        super("이미 투표권을 행사한 투표입니다.");
    }
}

package com.sharemindteam.votesystem.global.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(String wrongTeam) {
        super("해당하는 팀이 존재하지 않습니다. : " + wrongTeam);
    }
}

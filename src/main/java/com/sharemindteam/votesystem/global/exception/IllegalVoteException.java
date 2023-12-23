package com.sharemindteam.votesystem.global.exception;

import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.global.content.Team;

public class IllegalVoteException extends RuntimeException {
    public IllegalVoteException(Team team) {
        super("본인의 소속팀 " + team.name() + "에는 투표할 수 없습니다.");
    }

    public IllegalVoteException(Part part) {
        super("본인의 소속 파트가 아닌 " + part.name() + "에는 투표할 수 없습니다.");
    }
}

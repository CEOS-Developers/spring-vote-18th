package com.sharemindteam.votesystem.global.content;

import com.sharemindteam.votesystem.global.exception.PartNotFoundException;
import com.sharemindteam.votesystem.global.exception.TeamNotFoundException;
import java.util.Arrays;

public enum Team {
    SHARE_MIND,
    LOCAL_MOOD,
    REDI,
    SNIFF,
    GOTCHA;

    public static Team getTeamByName(String name) {
        return Arrays.stream(Team.values())
                .filter(team -> team.getName().equalsIgnoreCase(name))
                .findAny().orElseThrow(() -> new TeamNotFoundException(name));
    }

    private String getName() {
        return this.name();
    }
}

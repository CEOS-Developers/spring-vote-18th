package com.sharemindteam.votesystem.global.content;

import com.sharemindteam.votesystem.global.exception.PartNotFoundException;

import java.util.Arrays;

public enum Part {
    FRONTEND,
    BACKEND;

    public static Part getPartByName(String name) {
        return Arrays.stream(Part.values())
                .filter(part -> part.getName().equalsIgnoreCase(name))
                .findAny().orElseThrow(() -> new PartNotFoundException(name));
    }

    private String getName() {
        return this.name();
    }
}

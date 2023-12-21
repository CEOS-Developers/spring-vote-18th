package com.sharemindteam.votesystem.user.mapper;

import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.global.content.Team;
import com.sharemindteam.votesystem.user.dto.request.CreateUserRequest;
import com.sharemindteam.votesystem.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(CreateUserRequest createUserRequest) {
        return User.builder().loginId(createUserRequest.getLoginId()).password(createUserRequest.getPassword()).email(
                        createUserRequest.getEmail()).name(createUserRequest.getName())
                .part(Part.getPartByName(createUserRequest.getPartName())).team(
                        Team.getTeamByName((createUserRequest.getTeamName()))).build();
    }
}

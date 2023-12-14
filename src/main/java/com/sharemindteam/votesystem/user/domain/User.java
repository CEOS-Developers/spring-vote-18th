package com.sharemindteam.votesystem.user.domain;

import com.sharemindteam.votesystem.global.common.BaseEntity;
import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.global.content.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id")
    private String loginId;

    private String password;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(EnumType.STRING)
    private Team team;

    @Column(name = "voted_part")
    private boolean votedPart;

    @Column(name = "voted_demoday")
    private boolean votedDemoday;

    public void updateVotedDemoday() {
        this.votedDemoday = true;
    }

    public void updateVotedPartLeader() {
        this.votedPart = true;
    }
}

package com.sharemindteam.votesystem.user.domain;

import com.sharemindteam.votesystem.global.common.BaseEntity;
import com.sharemindteam.votesystem.global.content.Part;
import com.sharemindteam.votesystem.global.content.Team;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public void encodePassword(String password) {
        this.password = password;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Builder
    public User(String loginId, String password, String email, String name, Part part, Team team) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.part = part;
        this.team = team;
        this.votedPart = false;
        this.votedDemoday = false;
        this.roles = new ArrayList<>() {{
            add(Role.ROLE_USER);
        }};
    }
}

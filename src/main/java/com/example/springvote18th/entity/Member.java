package com.example.springvote18th.entity;

import com.example.springvote18th.entity.base.BaseTimeEntity;
import com.example.springvote18th.entity.enums.Part;
import com.example.springvote18th.entity.enums.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<MemberProject> memberProjects = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberPartLeader> memberPartLeaders = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Team teamName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Part part;

    private Boolean isVerified;

    @Builder
    public Member(Long id, String username, String password, String name, String email, Team teamName, Part part, Boolean isVerified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.teamName = teamName;
        this.part = part;
        this.isVerified = isVerified;
    }

    public void updateIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
}

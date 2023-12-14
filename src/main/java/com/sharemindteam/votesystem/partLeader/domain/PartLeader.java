package com.sharemindteam.votesystem.partLeader.domain;

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
public class PartLeader extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_leader_id")
    private Long partLeaderId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(EnumType.STRING)
    private Team team;
    
    private Integer votes;

    public void increaseVotes() {
        votes++;
    }
}

package com.sharemindteam.votesystem.demoday.domain;

import com.sharemindteam.votesystem.global.common.BaseEntity;
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
public class Demoday extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demoday_id")
    private Long demodayId;

    @Enumerated(EnumType.STRING)
    private Team team;

    private Integer votes;

    public void increaseVotes() {
        votes++;
    }
}

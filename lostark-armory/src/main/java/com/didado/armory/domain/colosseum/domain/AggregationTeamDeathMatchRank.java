package com.didado.armory.domain.colosseum.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AggregationTeamDeathMatchRank {
    @Id
    @GeneratedValue
    @Column(name = "aggregation_team_death_match_rank_id")
    private Long id;

    private Integer rank;

    private String rankName;

    private String rankIcon;

    private Integer rankLastMmr;

    private Integer playCount;

    private Integer victoryCount;

    private Integer loseCount;

    private Integer tieCount;

    private Integer killCount;

    private Integer aceCount;

    private Integer deathCount;
}

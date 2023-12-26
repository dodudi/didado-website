package com.didado.armory.domain.colosseum.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AggregationElimination {
    @Id
    @GeneratedValue
    @Column(name = "aggregation_elimination_id")
    private Integer firstWinCount;

    private Integer secondWinCount;

    private Integer thirdWinCount;

    private Integer firstPlayCount;

    private Integer secondPlayCount;

    private Integer thirdPlayCount;

    private Integer allKillCount;

    private Integer playCount;

    private Integer victoryCount;

    private Integer loseCount;

    private Integer tieCount;

    private Integer killCount;

    private Integer aceCount;

    private Integer deathCount;
}

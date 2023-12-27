package com.didado.armory.domain.colosseum.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CoOpBattleAggregation {
    @Id
    @GeneratedValue
    @Column(name = "coop_aggregation_id")
    private Long id;

    private Integer playCount;

    private Integer victoryCount;

    private Integer loseCount;

    private Integer tieCount;

    private Integer killCount;

    private Integer aceCount;

    private Integer deathCount;

    protected CoOpBattleAggregation(){}

    @Builder
    public CoOpBattleAggregation(Integer playCount, Integer victoryCount, Integer loseCount, Integer tieCount, Integer killCount, Integer aceCount, Integer deathCount) {
        this.playCount = playCount;
        this.victoryCount = victoryCount;
        this.loseCount = loseCount;
        this.tieCount = tieCount;
        this.killCount = killCount;
        this.aceCount = aceCount;
        this.deathCount = deathCount;
    }
}

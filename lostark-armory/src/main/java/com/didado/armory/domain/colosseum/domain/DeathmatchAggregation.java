package com.didado.armory.domain.colosseum.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class DeathmatchAggregation {
    @Id
    @GeneratedValue
    @Column(name = "death_match_aggregation_id")
    private Long id;

    private Integer playCount;

    private Integer victoryCount;

    private Integer loseCount;

    private Integer tieCount;

    private Integer killCount;

    private Integer aceCount;

    private Integer deathCount;

    protected DeathmatchAggregation() {
    }

    @Builder
    public DeathmatchAggregation(Integer playCount, Integer victoryCount, Integer loseCount, Integer tieCount, Integer killCount, Integer aceCount, Integer deathCount) {
        this.playCount = playCount;
        this.victoryCount = victoryCount;
        this.loseCount = loseCount;
        this.tieCount = tieCount;
        this.killCount = killCount;
        this.aceCount = aceCount;
        this.deathCount = deathCount;
    }

    public void changeData(DeathmatchAggregation deathmatchAggregation) {
        this.playCount = deathmatchAggregation.getPlayCount();
        this.victoryCount = deathmatchAggregation.getVictoryCount();
        this.loseCount = deathmatchAggregation.getLoseCount();
        this.tieCount = deathmatchAggregation.getTieCount();
        this.killCount = deathmatchAggregation.getKillCount();
        this.aceCount = deathmatchAggregation.getAceCount();
        this.deathCount = deathmatchAggregation.getDeathCount();
    }
}

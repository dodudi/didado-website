package com.didado.armory.domain.colosseum.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class AggregationElimination {
    @Id
    @GeneratedValue
    @Column(name = "aggregation_elimination_id")
    private Long id;

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

    protected AggregationElimination() {
    }

    @Builder
    public AggregationElimination(Integer firstWinCount, Integer secondWinCount, Integer thirdWinCount, Integer firstPlayCount, Integer secondPlayCount, Integer thirdPlayCount, Integer allKillCount, Integer playCount, Integer victoryCount, Integer loseCount, Integer tieCount, Integer killCount, Integer aceCount, Integer deathCount) {
        this.firstWinCount = firstWinCount;
        this.secondWinCount = secondWinCount;
        this.thirdWinCount = thirdWinCount;
        this.firstPlayCount = firstPlayCount;
        this.secondPlayCount = secondPlayCount;
        this.thirdPlayCount = thirdPlayCount;
        this.allKillCount = allKillCount;
        this.playCount = playCount;
        this.victoryCount = victoryCount;
        this.loseCount = loseCount;
        this.tieCount = tieCount;
        this.killCount = killCount;
        this.aceCount = aceCount;
        this.deathCount = deathCount;
    }

    public void changeData(AggregationElimination aggregationElimination){
        this.firstWinCount = aggregationElimination.getFirstWinCount();
        this.secondWinCount = aggregationElimination.getSecondWinCount();
        this.thirdWinCount = aggregationElimination.getThirdWinCount();
        this.firstPlayCount = aggregationElimination.getFirstPlayCount();
        this.secondPlayCount = aggregationElimination.getSecondPlayCount();
        this.thirdPlayCount = aggregationElimination.getThirdPlayCount();
        this.allKillCount = aggregationElimination.getAllKillCount();
        this.playCount = aggregationElimination.getPlayCount();
        this.victoryCount = aggregationElimination.getVictoryCount();
        this.loseCount = aggregationElimination.getLoseCount();
        this.tieCount = aggregationElimination.getTieCount();
        this.killCount = aggregationElimination.getKillCount();
        this.aceCount = aggregationElimination.getAceCount();
        this.deathCount = aggregationElimination.getDeathCount();
    }
}

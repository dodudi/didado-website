package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.AggregationElimination;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AggregationEliminationParameter {
    @JsonProperty(value = "FirstWinCount")
    private Integer firstWinCount;

    @JsonProperty(value = "SecondWinCount")
    private Integer secondWinCount;

    @JsonProperty(value = "ThirdWinCount")
    private Integer thirdWinCount;

    @JsonProperty(value = "FirstPlayCount")
    private Integer firstPlayCount;

    @JsonProperty(value = "SecondPlayCount")
    private Integer secondPlayCount;

    @JsonProperty(value = "ThirdPlayCount")
    private Integer thirdPlayCount;

    @JsonProperty(value = "AllKillCount")
    private Integer allKillCount;

    @JsonProperty(value = "PlayCount")
    private Integer playCount;

    @JsonProperty(value = "VictoryCount")
    private Integer victoryCount;

    @JsonProperty(value = "LoseCount")
    private Integer loseCount;

    @JsonProperty(value = "TieCount")
    private Integer tieCount;

    @JsonProperty(value = "KillCount")
    private Integer killCount;

    @JsonProperty(value = "AceCount")
    private Integer aceCount;

    @JsonProperty(value = "DeathCount")
    private Integer deathCount;

    public AggregationElimination toAggregationElimination() {
        return AggregationElimination.builder()
                .firstWinCount(firstWinCount)
                .secondWinCount(secondWinCount)
                .thirdWinCount(thirdWinCount)
                .firstPlayCount(firstPlayCount)
                .secondPlayCount(secondPlayCount)
                .thirdPlayCount(thirdPlayCount)
                .allKillCount(allKillCount)
                .playCount(playCount)
                .victoryCount(victoryCount)
                .loseCount(loseCount)
                .tieCount(tieCount)
                .killCount(killCount)
                .aceCount(aceCount)
                .deathCount(deathCount)
                .build();
    }
}

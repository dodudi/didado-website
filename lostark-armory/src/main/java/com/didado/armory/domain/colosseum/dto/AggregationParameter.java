package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.CoOpBattleAggregation;
import com.didado.armory.domain.colosseum.domain.DeathmatchAggregation;
import com.didado.armory.domain.colosseum.domain.TeamDeathmatchAggregation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AggregationParameter {
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

    public CoOpBattleAggregation toCoOpBattleAggregation() {
        return CoOpBattleAggregation.builder()
                .playCount(playCount)
                .victoryCount(victoryCount)
                .loseCount(loseCount)
                .tieCount(tieCount)
                .killCount(killCount)
                .aceCount(aceCount)
                .deathCount(deathCount)
                .build();
    }

    public DeathmatchAggregation toDeathmatchAggregation() {
        return DeathmatchAggregation.builder()
                .playCount(playCount)
                .victoryCount(victoryCount)
                .loseCount(loseCount)
                .tieCount(tieCount)
                .killCount(killCount)
                .aceCount(aceCount)
                .deathCount(deathCount)
                .build();
    }

    public TeamDeathmatchAggregation toTeamDeathmatchAggregation() {
        return TeamDeathmatchAggregation.builder()
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

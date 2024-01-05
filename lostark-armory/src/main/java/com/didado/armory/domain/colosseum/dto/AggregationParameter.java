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
    @JsonProperty("PlayCount")
    private int playCount;

    @JsonProperty("VictoryCount")
    private int victoryCount;

    @JsonProperty("LoseCount")
    private int loseCount;

    @JsonProperty("TieCount")
    private int tieCount;

    @JsonProperty("KillCount")
    private int killCount;

    @JsonProperty("AceCount")
    private int aceCount;

    @JsonProperty("DeathCount")
    private int deathCount;

    protected AggregationParameter() {

    }

    public AggregationParameter(CoOpBattleAggregation coOpBattleAggregation) {
        if (coOpBattleAggregation == null)
            return;

        this.playCount = coOpBattleAggregation.getPlayCount();
        this.victoryCount = coOpBattleAggregation.getVictoryCount();
        this.loseCount = coOpBattleAggregation.getLoseCount();
        this.tieCount = coOpBattleAggregation.getTieCount();
        this.killCount = coOpBattleAggregation.getKillCount();
        this.aceCount = coOpBattleAggregation.getAceCount();
        this.deathCount = coOpBattleAggregation.getDeathCount();
    }

    public AggregationParameter(DeathmatchAggregation deathMatchAggregation) {
        if (deathMatchAggregation == null)
            return;

        this.playCount = deathMatchAggregation.getPlayCount();
        this.victoryCount = deathMatchAggregation.getVictoryCount();
        this.loseCount = deathMatchAggregation.getLoseCount();
        this.tieCount = deathMatchAggregation.getTieCount();
        this.killCount = deathMatchAggregation.getKillCount();
        this.aceCount = deathMatchAggregation.getAceCount();
        this.deathCount = deathMatchAggregation.getDeathCount();
    }

    public AggregationParameter(TeamDeathmatchAggregation teamDeathMatchAggregation) {
        if (teamDeathMatchAggregation == null)
            return;

        this.playCount = teamDeathMatchAggregation.getPlayCount();
        this.victoryCount = teamDeathMatchAggregation.getVictoryCount();
        this.loseCount = teamDeathMatchAggregation.getLoseCount();
        this.tieCount = teamDeathMatchAggregation.getTieCount();
        this.killCount = teamDeathMatchAggregation.getKillCount();
        this.aceCount = teamDeathMatchAggregation.getAceCount();
        this.deathCount = teamDeathMatchAggregation.getDeathCount();
    }

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

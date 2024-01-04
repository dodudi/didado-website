package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class ColosseumParameter {

    @JsonProperty(value = "SeasonName")
    private String seasonName;

    @JsonProperty(value = "Competitive")
    private AggregationTeamDeathMatchRankParameter competitive;

    @JsonProperty(value = "TeamDeathmatch")
    private AggregationParameter teamDeathmatch;

    @JsonProperty(value = "Deathmatch")
    private AggregationParameter deathmatch;

    @JsonProperty(value = "TeamElimination")
    private AggregationEliminationParameter teamElimination;

    @JsonProperty(value = "CoOpBattle")
    private AggregationParameter coOpBattle;

    protected ColosseumParameter() {
    }

    public ColosseumParameter(Colosseum colosseum) {
        this.seasonName = colosseum.getSeasonName();
    }

    public Colosseum toColosseum() {
        return Colosseum.builder()
                .seasonName(seasonName)
                .build();
    }

    public void changeCoOpBattleAggregation(AggregationParameter coOpBattle) {
        this.coOpBattle = coOpBattle;
    }

    public void changeDeathmatchAggregation(AggregationParameter deathmatch) {
        this.deathmatch = deathmatch;
    }

    public void changeTeamDeathmatchAggregation(AggregationParameter teamDeathmatch) {
        this.teamDeathmatch = teamDeathmatch;
    }

    public void changeTeamElimination(AggregationEliminationParameter teamElimination) {
        this.teamElimination = teamElimination;
    }

    public void changeAggregationTeamDeathMatchRank(AggregationTeamDeathMatchRankParameter competitive) {
        this.competitive = competitive;
    }
}

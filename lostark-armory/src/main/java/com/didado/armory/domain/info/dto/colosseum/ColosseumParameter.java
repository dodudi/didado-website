package com.didado.armory.domain.info.dto.colosseum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
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
}

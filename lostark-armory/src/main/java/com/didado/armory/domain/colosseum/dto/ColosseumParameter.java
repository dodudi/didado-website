package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.Colosseum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

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

    public Colosseum toColosseum(){
        return Colosseum.builder()
                .seasonName(seasonName)
                .build();
    }
}

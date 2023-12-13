package com.didado.content.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Colosseum {

    @JsonProperty(value = "SeasonName")
    private String seasonName;

    @JsonProperty(value = "Competitive")
    private AggregationTeamDeathMatchRank competitive;

    @JsonProperty(value = "TeamDeathmatch")
    private Aggregation teamDeathmatch;

    @JsonProperty(value = "Deathmatch")
    private Aggregation deathmatch;

    @JsonProperty(value = "TeamElimination")
    private AggregationElimination teamElimination;

    @JsonProperty(value = "CoOpBattle")
    private Aggregation coOpBattle;
}

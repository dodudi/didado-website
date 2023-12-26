package com.didado.armory.domain.info.dto.colosseum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AggregationTeamDeathMatchRankParameter {
    @JsonProperty(value = "Rank")
    private Integer rank;

    @JsonProperty(value = "RankName")
    private String rankName;

    @JsonProperty(value = "RankIcon")
    private String rankIcon;

    @JsonProperty(value = "RankLastMmr")
    private Integer rankLastMmr;

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
}

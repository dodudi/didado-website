package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.AggregationTeamDeathMatchRank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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

    public AggregationTeamDeathMatchRank toAggregationTeamDeathMatchRank() {
        return AggregationTeamDeathMatchRank.builder()
                .rank(rank)
                .rankName(rankName)
                .rankIcon(rankIcon)
                .rankLastMmr(rankLastMmr)
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

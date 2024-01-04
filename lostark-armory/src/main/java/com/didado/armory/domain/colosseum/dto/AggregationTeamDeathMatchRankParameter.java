package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.AggregationTeamDeathMatchRank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

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

    protected AggregationTeamDeathMatchRankParameter() {
    }

    public AggregationTeamDeathMatchRankParameter(AggregationTeamDeathMatchRank competitive) {
        if (competitive == null)
            return;

        this.rank = competitive.getRank();
        this.rankName = competitive.getRankName();
        this.rankIcon = competitive.getRankIcon();
        this.rankLastMmr = competitive.getRankLastMmr();
        this.playCount = competitive.getPlayCount();
        this.victoryCount = competitive.getVictoryCount();
        this.loseCount = competitive.getLoseCount();
        this.tieCount = competitive.getTieCount();
        this.killCount = competitive.getKillCount();
        this.aceCount = competitive.getAceCount();
        this.deathCount = competitive.getDeathCount();
    }

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

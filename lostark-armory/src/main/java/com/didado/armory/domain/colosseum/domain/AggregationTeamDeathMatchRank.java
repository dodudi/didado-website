package com.didado.armory.domain.colosseum.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class AggregationTeamDeathMatchRank {
    @Id
    @GeneratedValue
    @Column(name = "aggregation_team_death_match_rank_id")
    private Long id;

    private Integer rank;

    private String rankName;

    private String rankIcon;

    private Integer rankLastMmr;

    private Integer playCount;

    private Integer victoryCount;

    private Integer loseCount;

    private Integer tieCount;

    private Integer killCount;

    private Integer aceCount;

    private Integer deathCount;

    protected AggregationTeamDeathMatchRank() {
    }

    @Builder
    public AggregationTeamDeathMatchRank(Integer rank, String rankName, String rankIcon, Integer rankLastMmr, Integer playCount, Integer victoryCount, Integer loseCount, Integer tieCount, Integer killCount, Integer aceCount, Integer deathCount) {
        this.rank = rank;
        this.rankName = rankName;
        this.rankIcon = rankIcon;
        this.rankLastMmr = rankLastMmr;
        this.playCount = playCount;
        this.victoryCount = victoryCount;
        this.loseCount = loseCount;
        this.tieCount = tieCount;
        this.killCount = killCount;
        this.aceCount = aceCount;
        this.deathCount = deathCount;
    }

    public void changeData(AggregationTeamDeathMatchRank aggregationTeamDeathMatchRank) {
        this.rank = aggregationTeamDeathMatchRank.getRank();
        this.rankName = aggregationTeamDeathMatchRank.getRankName();
        this.rankIcon = aggregationTeamDeathMatchRank.getRankIcon();
        this.rankLastMmr = aggregationTeamDeathMatchRank.getRankLastMmr();
        this.playCount = aggregationTeamDeathMatchRank.getPlayCount();
        this.victoryCount = aggregationTeamDeathMatchRank.getVictoryCount();
        this.loseCount = aggregationTeamDeathMatchRank.getLoseCount();
        this.tieCount = aggregationTeamDeathMatchRank.getTieCount();
        this.killCount = aggregationTeamDeathMatchRank.getKillCount();
        this.aceCount = aggregationTeamDeathMatchRank.getAceCount();
        this.deathCount = aggregationTeamDeathMatchRank.getDeathCount();
    }
}

package com.didado.armory.domain.colosseum.domain;

import com.didado.armory.domain.colosseum.dto.AggregationEliminationParameter;
import com.didado.armory.domain.colosseum.dto.AggregationParameter;
import com.didado.armory.domain.colosseum.dto.AggregationTeamDeathMatchRankParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Colosseum {
    @Id
    @GeneratedValue
    @Column(name = "colosseum_id")
    private Long id;

    private String seasonName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colosseum_info_id")
    private ColosseumInfo colosseumInfo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aggregation_team_death_match_rank_id")
    private AggregationTeamDeathMatchRank competitive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_death_match_aggregation_id")
    private TeamDeathmatchAggregation teamDeathmatch;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "death_match_aggregation_id")
    private DeathmatchAggregation deathmatch;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aggregation_elimination_id")
    private AggregationElimination teamElimination;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "coop_aggregation_id")
    private CoOpBattleAggregation coOpBattle;

    protected Colosseum() {
    }

    @Builder
    public Colosseum(String seasonName) {
        this.seasonName = seasonName;
    }

    public void changeCoOpBattleAggregation(CoOpBattleAggregation coOpBattle) {
        this.coOpBattle = coOpBattle;
    }

    public void changeDeathmatchAggregation(DeathmatchAggregation deathmatch) {
        this.deathmatch = deathmatch;
    }

    public void changeTeamDeathmatchAggregation(TeamDeathmatchAggregation teamDeathmatch) {
        this.teamDeathmatch = teamDeathmatch;
    }

    public void changeTeamElimination(AggregationElimination teamElimination) {
        this.teamElimination = teamElimination;
    }

    public void changeAggregationTeamDeathMatchRank(AggregationTeamDeathMatchRank competitive) {
        this.competitive = competitive;
    }

    public void changeColosseumInfo(ColosseumInfo colosseumInfo) {
        this.colosseumInfo = colosseumInfo;
        colosseumInfo.getColosseums().add(this);
    }

    public void deleteColosseumInfo() {
        colosseumInfo.getColosseums().remove(this);
        this.colosseumInfo = null;
    }

    public void changeData(Colosseum colosseum) {
        this.seasonName = colosseum.getSeasonName();
    }
}

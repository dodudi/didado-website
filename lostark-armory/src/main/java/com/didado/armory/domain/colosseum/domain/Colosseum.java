package com.didado.armory.domain.colosseum.domain;

import com.didado.armory.domain.colosseum.dto.AggregationEliminationParameter;
import com.didado.armory.domain.colosseum.dto.AggregationParameter;
import com.didado.armory.domain.colosseum.dto.AggregationTeamDeathMatchRankParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Colosseum {
    @Id
    @GeneratedValue
    @Column(name = "colosseum_id")
    private Long id;

    private String seasonName;


    @OneToOne
    private AggregationTeamDeathMatchRank competitive;

    @OneToOne
    private Aggregation teamDeathmatch;

    @OneToOne
    private Aggregation deathmatch;

    @OneToOne
    private AggregationElimination teamElimination;

    @OneToOne
    private Aggregation coOpBattle;
}

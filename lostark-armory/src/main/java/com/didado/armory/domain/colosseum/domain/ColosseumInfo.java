package com.didado.armory.domain.colosseum.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ColosseumInfo {
    @Id
    @GeneratedValue
    @Column(name = "colosseum_info_id")
    private Long id;

    private Integer rank;

    private Integer preRank;

    private Integer exp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colosseumInfo")
    private List<Colosseum> colosseums = new ArrayList<>();

    protected ColosseumInfo() {
    }

    @Builder
    public ColosseumInfo(Integer rank, Integer preRank, Integer exp) {
        this.rank = rank;
        this.preRank = preRank;
        this.exp = exp;
    }
}

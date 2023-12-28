package com.didado.armory.domain.colosseum.domain;

import com.didado.armory.domain.colosseum.dto.ColosseumParameter;
import com.didado.armory.domain.info.domain.Armory;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_id")
    private Armory armory;

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

    public void changeArmory(Armory armory) {
        this.armory = armory;
        armory.changeColosseumInfo(this);
    }
}

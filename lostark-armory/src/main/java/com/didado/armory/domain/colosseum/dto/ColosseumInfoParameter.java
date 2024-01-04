package com.didado.armory.domain.colosseum.dto;

import com.didado.armory.domain.colosseum.domain.Colosseum;
import com.didado.armory.domain.colosseum.domain.ColosseumInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class ColosseumInfoParameter {
    @JsonProperty(value = "Rank")
    private Integer rank;

    @JsonProperty(value = "PreRank")
    private Integer preRank;

    @JsonProperty(value = "Exp")
    private Integer exp;

    @JsonProperty(value = "Colosseums")
    private List<ColosseumParameter> colosseums = new ArrayList<>();

    protected ColosseumInfoParameter() {
    }

    public ColosseumInfoParameter(ColosseumInfo colosseumInfo) {
        this.rank = colosseumInfo.getRank();
        this.preRank = colosseumInfo.getPreRank();
        this.exp = colosseumInfo.getExp();
    }

    public ColosseumInfo toColosseumInfo(String characterName) {
        return ColosseumInfo.builder()
                .characterName(characterName)
                .rank(rank)
                .preRank(preRank)
                .exp(exp)
                .build();
    }
}

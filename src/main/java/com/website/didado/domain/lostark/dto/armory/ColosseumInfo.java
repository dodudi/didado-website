package com.website.didado.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ColosseumInfo {
    @JsonProperty(value = "Rank")
    private Integer rank;

    @JsonProperty(value = "PreRank")
    private Integer preRank;

    @JsonProperty(value = "Exp")
    private Integer exp;

    @JsonProperty(value = "Colosseums")
    private List<Colosseum> colosseums;
}

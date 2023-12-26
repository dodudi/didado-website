package com.didado.armory.domain.colosseum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

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
    private List<ColosseumParameter> colosseums;
}

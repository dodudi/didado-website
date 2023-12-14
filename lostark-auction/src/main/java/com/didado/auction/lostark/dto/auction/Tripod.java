package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Tripod {

    @JsonProperty(value = "Value")
    private Integer value;

    @JsonProperty(value = "Text")
    private String text;

    @JsonProperty(value = "IsGem")
    private Boolean isGem;
}

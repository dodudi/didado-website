package com.website.didado.domain.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SearchDetailOption {

    @JsonProperty(value = "FirstOption")
    private Integer firstOption;

    @JsonProperty(value = "SecondOption")
    private Integer secondOption;

    @JsonProperty(value = "MinValue")
    private Integer minValue;

    @JsonProperty(value = "MaxValue")
    private Integer maxValue;
}

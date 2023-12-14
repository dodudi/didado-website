package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EtcSub {
    @JsonProperty(value = "Value")
    private Integer value;

    @JsonProperty(value = "Text")
    private String text;

    @JsonProperty(value = "Class")
    private String className;
}

package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class EtcOption {

    @JsonProperty(value = "Value")
    private Integer value;

    @JsonProperty(value = "Text")
    private String text;

    @JsonProperty(value = "EtcSubs")
    private List<EtcSub> etcSubs;
}

package com.didado.content.domain.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ItemOption {
    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "OptionName")
    private String optionName;

    @JsonProperty(value = "OptionNameTripod")
    private String optionNameTripod;

    @JsonProperty(value = "Value")
    private String value;

    @JsonProperty(value = "IsPenalty")
    private String isPenalty;

    @JsonProperty(value = "ClassName")
    private String className;
}

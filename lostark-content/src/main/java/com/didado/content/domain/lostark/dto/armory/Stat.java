package com.didado.content.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Stat {
    @JsonProperty(value = "Type")
    private String type;
    @JsonProperty(value = "Value")
    private String value;
    @JsonProperty(value = "Tooltip")
    private List<String> toolTip;
}

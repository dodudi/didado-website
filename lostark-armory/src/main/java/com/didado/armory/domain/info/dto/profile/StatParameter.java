package com.didado.armory.domain.info.dto.profile;

import com.didado.armory.domain.profile.domain.Stat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class StatParameter {
    @JsonProperty(value = "Type")
    private String type;
    @JsonProperty(value = "Value")
    private String value;
    @JsonProperty(value = "Tooltip")
    private List<String> toolTip;

}

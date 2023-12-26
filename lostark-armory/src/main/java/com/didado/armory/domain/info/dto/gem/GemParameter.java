package com.didado.armory.domain.info.dto.gem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GemParameter {

    @JsonProperty(value = "Slot")
    private String slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Level")
    private String level;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Tooltip")
    private String toolTip;
    
}

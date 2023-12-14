package com.didado.armory.domain.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SkillTripod {
    @JsonProperty(value = "Tier")
    private Integer tier;

    @JsonProperty(value = "Slot")
    private Integer slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Level")
    private Integer level;

    @JsonProperty(value = "IsSelected")
    private Boolean isSelected;

    @JsonProperty(value = "Tooltip")
    private String toolTip;
}

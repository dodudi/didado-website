package com.didado.armory.domain.gem.dto;

import com.didado.armory.domain.gem.domain.Gem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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


    public Gem toGem() {
        return Gem.builder()
                .slot(slot)
                .name(name)
                .icon(icon)
                .level(level)
                .grade(grade)
                .toolTip(toolTip)
                .build();
    }
}

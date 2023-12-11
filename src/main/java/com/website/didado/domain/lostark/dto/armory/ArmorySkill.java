package com.website.didado.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmorySkill {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Level")
    private Integer level;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "IsAwakening")
    private Boolean isAwakening;

    @JsonProperty(value = "Tripods")
    private List<SkillTripod> tripods;

    @JsonProperty(value = "Rune")
    private SkillRune rune;

    @JsonProperty(value = "Tooltip")
    private String toolTip;
}

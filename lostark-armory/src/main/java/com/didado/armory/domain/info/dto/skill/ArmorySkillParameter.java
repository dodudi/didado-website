package com.didado.armory.domain.info.dto.skill;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmorySkillParameter {
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
    private List<SkillTripodParameter> tripods;

    @JsonProperty(value = "Rune")
    private SkillRuneParameter rune;

    @JsonProperty(value = "Tooltip")
    private String toolTip;
}

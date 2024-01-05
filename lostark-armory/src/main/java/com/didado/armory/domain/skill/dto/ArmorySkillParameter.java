package com.didado.armory.domain.skill.dto;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
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
    private List<SkillTripodParameter> tripods = new ArrayList<>();

    @JsonProperty(value = "Rune")
    private SkillRuneParameter rune;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    protected ArmorySkillParameter() {
    }

    public ArmorySkillParameter(ArmorySkill armorySkill) {
        this.name = armorySkill.getName();
        this.icon = armorySkill.getIcon();
        this.level = armorySkill.getLevel();
        this.type = armorySkill.getType();
        this.isAwakening = armorySkill.getIsAwakening();
        this.toolTip = armorySkill.getToolTip();
    }

    public ArmorySkill toArmorySkill() {
        return ArmorySkill.builder()
                .name(name)
                .icon(icon)
                .level(level)
                .type(type)
                .isAwakening(isAwakening)
                .toolTip(toolTip)
                .build();
    }

    public void changeSkillRuneParameter(SkillRuneParameter skillRuneParameter) {
        this.rune = skillRuneParameter;
    }
}

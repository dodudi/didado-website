package com.didado.armory.domain.skill.dto;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SkillRuneParameter {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    protected SkillRuneParameter() {
    }

    public SkillRuneParameter(SkillRune skillRune) {
        this.name = skillRune.getName();
        this.icon = skillRune.getIcon();
        this.grade = skillRune.getGrade();
        this.toolTip = skillRune.getToolTip();
    }

    public SkillRune toSkillRune(ArmorySkill armorySkill) {

        SkillRune convertSkillRune = SkillRune.builder()
                .name(name)
                .icon(icon)
                .grade(grade)
                .toolTip(toolTip)
                .build();

        convertSkillRune.updateArmorySkill(armorySkill);
        return convertSkillRune;
    }
}

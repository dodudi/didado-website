package com.didado.armory.domain.skill.domain;

import com.didado.armory.domain.skill.dto.SkillRuneParameter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class SkillRune {
    @Id
    @GeneratedValue
    @Column(name = "skill_rune_id")
    private Long id;
    private String name;
    private String icon;
    private String grade;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    @OneToOne(fetch = FetchType.LAZY)
    private ArmorySkill armorySkill;

    protected SkillRune() {
    }

    @Builder
    public SkillRune(String name, String icon, String grade, String toolTip) {
        this.name = name;
        this.icon = icon;
        this.grade = grade;
        this.toolTip = toolTip;
    }

    public void updateData(SkillRuneParameter skillRuneParameter) {
        this.name = skillRuneParameter.getName();
        this.icon = skillRuneParameter.getIcon();
        this.grade = skillRuneParameter.getGrade();
        this.toolTip = skillRuneParameter.getToolTip();
    }

    public void updateArmorySkill(ArmorySkill armorySkill) {
        this.armorySkill = armorySkill;
    }
}

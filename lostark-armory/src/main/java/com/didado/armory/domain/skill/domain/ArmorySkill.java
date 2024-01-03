package com.didado.armory.domain.skill.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ArmorySkill {
    @Id
    @GeneratedValue
    @Column(name = "armory_skill_id")
    private Long id;

    private String name;

    private String icon;

    private Integer level;

    private String type;

    private Boolean isAwakening;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_info_id")
    private SkillInfo skillInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armorySkill")
    private List<SkillTripod> tripods = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armorySkill")
    private SkillRune rune;


    protected ArmorySkill() {
    }

    @Builder
    public ArmorySkill(String name, String icon, Integer level, String type, Boolean isAwakening, String toolTip) {
        this.name = name;
        this.icon = icon;
        this.level = level;
        this.type = type;
        this.isAwakening = isAwakening;
        this.toolTip = toolTip;
    }

    public void changeSkillRune(SkillRune skillRune) {
        this.rune = skillRune;
    }

    public void changeSkillInfo(SkillInfo skillInfo) {
        this.skillInfo = skillInfo;
        skillInfo.getArmorySkills().add(this);
    }

    public void deleteSkillInfo() {
        skillInfo.getArmorySkills().remove(this);
        this.skillInfo = null;
    }

    public void changeData(ArmorySkill armorySkill) {
        this.name = armorySkill.getName();
        this.icon = armorySkill.getIcon();
        this.level = armorySkill.getLevel();
        this.type = armorySkill.getType();
        this.isAwakening = armorySkill.getIsAwakening();
        this.toolTip = armorySkill.getToolTip();
    }
}

package com.didado.armory.domain.skill.domain;

import com.didado.armory.domain.info.domain.Armory;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_id")
    private Armory armory;


    private String name;

    private String icon;

    private Integer level;

    private String type;

    private Boolean isAwakening;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armorySkill")
    private List<SkillTripod> tripods = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armorySkill")
    private SkillRune rune;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

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

    public void changeArmory(Armory armory) {
        this.armory = armory;
        armory.getArmorySkills().add(this);
    }
}

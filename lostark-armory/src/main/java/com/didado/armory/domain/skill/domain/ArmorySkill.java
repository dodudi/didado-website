package com.didado.armory.domain.skill.domain;

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

    private String characterName;

    private String name;

    private String icon;

    private Integer level;

    private String type;

    private Boolean isAwakening;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armorySkill", cascade = CascadeType.ALL)
    private List<SkillTripod> tripods = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armorySkill",  cascade = CascadeType.ALL)
    private SkillRune rune;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    protected ArmorySkill() {
    }

    @Builder
    public ArmorySkill(String characterName, String name, String icon, Integer level, String type, Boolean isAwakening, String toolTip) {
        this.characterName = characterName;
        this.name = name;
        this.icon = icon;
        this.level = level;
        this.type = type;
        this.isAwakening = isAwakening;
        this.toolTip = toolTip;
    }
}

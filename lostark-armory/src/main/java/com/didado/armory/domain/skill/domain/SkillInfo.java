package com.didado.armory.domain.skill.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class SkillInfo {
    @Id
    @GeneratedValue
    @Column(name = "skill_info_id")
    private Long id;
    private String characterName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skillInfo")
    private List<ArmorySkill> armorySkills = new ArrayList<>();

    protected SkillInfo() {
    }

    public SkillInfo(String characterName) {
        this.characterName = characterName;
    }
}

package com.didado.armory.domain.skill.dto;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SkillInfoParameter {
    private String characterName;
    private List<ArmorySkillParameter> armorySkills = new ArrayList<>();

    protected SkillInfoParameter() {
    }

    public SkillInfoParameter(String characterName) {
        this.characterName = characterName;
    }
}

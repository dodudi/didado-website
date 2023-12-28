package com.didado.armory.domain.skill.repository;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArmorySkillRepository extends JpaRepository<ArmorySkill, Long> {
//    List<ArmorySkill> findArmorySkillByCharacterName(String characterName);
}

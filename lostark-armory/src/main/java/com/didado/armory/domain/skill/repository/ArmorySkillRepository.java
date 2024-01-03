package com.didado.armory.domain.skill.repository;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArmorySkillRepository extends JpaRepository<ArmorySkill, Long> {
    List<ArmorySkill> findBySkillInfoId(Long skillId);
}

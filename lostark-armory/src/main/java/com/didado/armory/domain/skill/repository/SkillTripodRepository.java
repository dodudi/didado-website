package com.didado.armory.domain.skill.repository;

import com.didado.armory.domain.skill.domain.SkillTripod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillTripodRepository extends JpaRepository<SkillTripod, Long> {
    List<SkillTripod> findByArmorySkillId(Long skillId);
}

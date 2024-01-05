package com.didado.armory.domain.skill.repository;

import com.didado.armory.domain.skill.domain.SkillRune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRuneRepository extends JpaRepository<SkillRune, Long> {
    Optional<SkillRune> findByArmorySkillId(Long skillId);
}

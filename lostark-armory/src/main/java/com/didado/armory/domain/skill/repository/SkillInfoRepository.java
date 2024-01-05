package com.didado.armory.domain.skill.repository;

import com.didado.armory.domain.skill.domain.SkillInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillInfoRepository extends JpaRepository<SkillInfo, Long> {
    boolean existsByCharacterName(String characterName);

    Optional<SkillInfo> findByCharacterName(String characterName);
}

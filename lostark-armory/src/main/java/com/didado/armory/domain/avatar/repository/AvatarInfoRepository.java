package com.didado.armory.domain.avatar.repository;

import com.didado.armory.domain.avatar.domain.ArmoryAvatarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarInfoRepository extends JpaRepository<ArmoryAvatarInfo, Long> {
    Optional<ArmoryAvatarInfo> findByCharacterName(String characterName);

    boolean existsByCharacterName(String characterName);
}

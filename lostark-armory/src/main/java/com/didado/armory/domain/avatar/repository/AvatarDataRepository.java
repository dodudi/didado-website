package com.didado.armory.domain.avatar.repository;

import com.didado.armory.domain.avatar.domain.AvatarData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarDataRepository extends JpaRepository<AvatarData, Long> {
    Optional<AvatarData> findByCharacterName(String characterName);

    boolean existsByCharacterName(String characterName);
}

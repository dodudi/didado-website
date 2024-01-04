package com.didado.armory.domain.collectible.repository;

import com.didado.armory.domain.collectible.domain.CollectibleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectibleInfoRepository extends JpaRepository<CollectibleInfo, Long> {
    boolean existsByCharacterName(String characterName);
    Optional<CollectibleInfo> findByCharacterName(String characterName);
}

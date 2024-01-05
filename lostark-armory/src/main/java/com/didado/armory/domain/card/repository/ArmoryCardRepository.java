package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.ArmoryCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArmoryCardRepository extends JpaRepository<ArmoryCard, Long> {
    Optional<ArmoryCard> findByCharacterName(String characterName);

    boolean existsByCharacterName(String characterName);
}

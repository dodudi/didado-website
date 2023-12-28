package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.ArmoryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmoryCardRepository extends JpaRepository<ArmoryCard, Long> {
}

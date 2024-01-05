package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByArmoryCardId(Long cardId);
}

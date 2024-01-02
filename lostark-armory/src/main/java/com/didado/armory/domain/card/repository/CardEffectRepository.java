package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.CardEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardEffectRepository extends JpaRepository<CardEffect, Long> {
    List<CardEffect> findByArmoryCardId(Long cardId);


    void deleteByArmoryCardId(Long cardId);
}

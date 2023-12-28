package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.CardEffect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardEffectRepository extends JpaRepository<CardEffect, Long> {
}

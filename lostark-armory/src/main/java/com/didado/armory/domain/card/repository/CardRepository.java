package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}

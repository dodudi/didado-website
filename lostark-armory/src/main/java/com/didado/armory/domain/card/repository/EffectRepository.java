package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EffectRepository extends JpaRepository<Effect, Long> {
}

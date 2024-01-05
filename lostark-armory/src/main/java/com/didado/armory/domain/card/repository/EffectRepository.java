package com.didado.armory.domain.card.repository;

import com.didado.armory.domain.card.domain.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EffectRepository extends JpaRepository<Effect, Long> {
    List<Effect> findByCardEffectId(Long effectId);
}

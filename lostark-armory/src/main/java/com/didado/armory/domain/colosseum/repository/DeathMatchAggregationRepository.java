package com.didado.armory.domain.colosseum.repository;

import com.didado.armory.domain.colosseum.domain.DeathmatchAggregation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeathMatchAggregationRepository extends JpaRepository<DeathmatchAggregation, Long> {
}

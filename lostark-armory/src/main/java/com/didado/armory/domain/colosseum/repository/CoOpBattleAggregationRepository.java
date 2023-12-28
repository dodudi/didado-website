package com.didado.armory.domain.colosseum.repository;

import com.didado.armory.domain.colosseum.domain.CoOpBattleAggregation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoOpBattleAggregationRepository extends JpaRepository<CoOpBattleAggregation, Long> {
}

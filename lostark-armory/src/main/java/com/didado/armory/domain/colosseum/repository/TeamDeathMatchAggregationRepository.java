package com.didado.armory.domain.colosseum.repository;

import com.didado.armory.domain.colosseum.domain.TeamDeathmatchAggregation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDeathMatchAggregationRepository extends JpaRepository<TeamDeathmatchAggregation, Long> {
}

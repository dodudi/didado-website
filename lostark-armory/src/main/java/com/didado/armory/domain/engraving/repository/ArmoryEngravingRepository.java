package com.didado.armory.domain.engraving.repository;

import com.didado.armory.domain.engraving.domain.ArmoryEngraving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmoryEngravingRepository extends JpaRepository<ArmoryEngraving, Long> {
}

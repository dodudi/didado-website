package com.didado.armory.domain.engraving.repository;

import com.didado.armory.domain.engraving.domain.Engraving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngravingRepository extends JpaRepository<Engraving, Long> {
}

package com.didado.armory.domain.gem.repository;

import com.didado.armory.domain.gem.domain.ArmoryGem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmoryGemRepository extends JpaRepository<ArmoryGem, Long> {
}

package com.didado.armory.domain.gem.repository;

import com.didado.armory.domain.gem.domain.Gem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GemRepository extends JpaRepository<Gem, Long> {
}

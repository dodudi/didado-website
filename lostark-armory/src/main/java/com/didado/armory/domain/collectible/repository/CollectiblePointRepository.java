package com.didado.armory.domain.collectible.repository;

import com.didado.armory.domain.collectible.domain.CollectiblePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectiblePointRepository extends JpaRepository<CollectiblePoint, Long> {
}

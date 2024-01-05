package com.didado.armory.domain.collectible.repository;

import com.didado.armory.domain.collectible.domain.CollectiblePoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectiblePointRepository extends JpaRepository<CollectiblePoint, Long> {
    List<CollectiblePoint> findByCollectibleId(Long collectibleId);
}

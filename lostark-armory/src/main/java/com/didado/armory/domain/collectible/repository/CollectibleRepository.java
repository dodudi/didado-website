package com.didado.armory.domain.collectible.repository;

import com.didado.armory.domain.collectible.domain.Collectible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectibleRepository extends JpaRepository<Collectible, Long> {
    List<Collectible> findByCollectibleInfoId(Long collectibleId);
}

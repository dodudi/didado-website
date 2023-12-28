package com.didado.armory.domain.info.repository;

import com.didado.armory.domain.info.domain.Armory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArmoryRepository extends JpaRepository<Armory, Long> {
    Optional<Armory> findArmoryByCharacterName(String characterName);
}

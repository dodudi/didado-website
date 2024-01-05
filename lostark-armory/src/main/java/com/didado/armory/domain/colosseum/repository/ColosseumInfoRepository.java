package com.didado.armory.domain.colosseum.repository;

import com.didado.armory.domain.colosseum.domain.ColosseumInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColosseumInfoRepository extends JpaRepository<ColosseumInfo, Long> {
    Optional<ColosseumInfo> findByCharacterName(String characterName);

    boolean existsByCharacterName(String characterName);
}

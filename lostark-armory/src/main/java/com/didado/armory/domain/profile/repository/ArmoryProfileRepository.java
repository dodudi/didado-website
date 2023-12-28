package com.didado.armory.domain.profile.repository;


import com.didado.armory.domain.info.domain.Armory;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArmoryProfileRepository extends JpaRepository<ArmoryProfile, Long> {
    @Query("select p from ArmoryProfile p where p.characterName = :characterName")
    Optional<ArmoryProfile> findByCharacterName(@Param("characterName") String characterName);

    Optional<ArmoryProfile> findArmoryProfileByArmoryId(Long armoryId);
}

package com.didado.armory.domain.avatar.repository;

import com.didado.armory.domain.avatar.domain.AvatarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AvatarDataRepository extends JpaRepository<AvatarData, Long> {
    Optional<AvatarData> findByCharacterName(String characterName);

    @Query("select a from AvatarData a join fetch a.avatars where a.characterName = :characterName")
    Optional<AvatarData> findByCharacterNameFetch(@Param("characterName") String characterName);

    boolean existsByCharacterName(String characterName);
}

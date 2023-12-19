package com.didado.character.domain.lostark.repository;

import com.didado.character.domain.lostark.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("select c from Character c join fetch c.characterInfos where c.id=:characterId")
    Optional<Character> findCharacterByCharacterIdFetch(@Param("characterId") Long characterId);
}

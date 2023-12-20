package com.didado.character.domain.lostark.repository;

import com.didado.character.domain.lostark.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
//    @Query("select c from Character c join fetch c.characterInfos where c.id=:characterId")
//    Optional<Character> findCharacterByCharacterIdFetch(@Param("characterId") Long characterId);

    @Query("select c from Character c join fetch c.characterInfos where c.id in (select i.character.id from CharacterInfo i where i.characterName in (:characterName))")
    Optional<Character> findCharacterByCharacterIdFetch(@Param("characterName") List<String> characterName);

    @Query("select c from Character c join fetch c.characterInfos where c.id in (select i.character.id from CharacterInfo i where i.characterName = :characterName)")
    Optional<Character> findCharacterByCharacterIdFetch(@Param("characterName") String characterName);

    List<Character> findAllByUpdateTimeBetween(LocalDateTime before, LocalDateTime after);
}

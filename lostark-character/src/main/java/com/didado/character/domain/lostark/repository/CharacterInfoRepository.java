package com.didado.character.domain.lostark.repository;

import com.didado.character.domain.lostark.domain.CharacterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterInfoRepository extends JpaRepository<CharacterInfo, Long> {
    Optional<CharacterInfo> findByCharacterName(String characterName);

//    @Query("select i.character from CharacterInfo i where i.characterName in :characterNames")
//    Optional<Character> findCharacterInCharacterNames(@Param("characterNames") List<String> characterNames);

//    @Query("select i.character from CharacterInfo i where i.characterName=:characterName")
//    Optional<Character> findCharacterByCharacterName(@Param("characterName") String characterName);
}

package com.didado.character.domain.lostark.application;

import com.didado.character.domain.lostark.domain.Character;
import com.didado.character.domain.lostark.domain.CharacterInfo;
import com.didado.character.domain.lostark.dto.CharacterParameter;
import com.didado.character.domain.lostark.repository.CharacterInfoRepository;
import com.didado.character.domain.lostark.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterSearchService {
    private final CharacterRepository characterRepository;

    public List<CharacterParameter> search(String characterName) {
        Character character = characterRepository.findCharacterByCharacterIdFetch(characterName)
                .orElseThrow(IllegalArgumentException::new);
        
        log.debug("Search CharacterName={}", characterName);
        log.debug("Find Character={}", character);

        List<CharacterInfo> characterInfos = character.getCharacterInfos();
        return characterInfos.stream()
                .map(characterInfo -> CharacterParameter.builder()
                        .serverName(characterInfo.getServerName())
                        .characterName(characterInfo.getCharacterName())
                        .characterLevel(characterInfo.getCharacterLevel())
                        .characterClassName(characterInfo.getCharacterClassName())
                        .itemAvgLevel(characterInfo.getItemAvgLevel())
                        .itemMaxLevel(characterInfo.getItemMaxLevel())
                        .build()
                ).toList();
    }
}

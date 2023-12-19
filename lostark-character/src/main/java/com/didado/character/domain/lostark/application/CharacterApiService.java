package com.didado.character.domain.lostark.application;

import com.didado.character.domain.lostark.domain.Character;
import com.didado.character.domain.lostark.domain.CharacterInfo;
import com.didado.character.domain.lostark.domain.LostarkProperty;
import com.didado.character.domain.lostark.dto.CharacterParameter;
import com.didado.character.domain.lostark.repository.CharacterInfoRepository;
import com.didado.character.domain.lostark.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterApiService {
    private final CharacterRepository characterRepository;
    private final CharacterInfoRepository characterInfoRepository;

    private final RestTemplate restTemplate;
    private final LostarkProperty lostarkProperty;

    @Transactional(readOnly = false)
    public Character search(String characterName) {
        List<CharacterParameter> parameters = get(characterName);
        if (parameters.isEmpty()) {
            characterInfoRepository.findByCharacterName(characterName)
                    .flatMap(info -> characterRepository.findById(info.getId()))
                    .ifPresent(characterRepository::delete);
            return null;
        }

        List<String> characterNames = parameters.stream()
                .map(CharacterParameter::getCharacterName)
                .toList();

//        Character character = characterInfoRepository.findCharacterInCharacterNames(characterNames)
//                .orElseGet(() -> null);

        Character character = characterRepository.findCharacterByCharacterIdFetch(characterNames)
                .orElseGet(() -> null);
        log.debug("Character={}", character);

        if (character == null) {
            Character newCharacter = new Character();
            characterRepository.save(newCharacter);

            List<CharacterInfo> list = parameters.stream()
                    .map(CharacterParameter::toCharacterInfo)
                    .map(characterInfo -> characterInfo.updateCharacter(newCharacter))
                    .map(characterInfoRepository::save)
                    .toList();

            return newCharacter;
        } else {
            List<CharacterInfo> characterInfos = character.getCharacterInfos();
            Map<String, CharacterParameter> parameterMap = parameters.stream()
                    .collect(Collectors.toMap(CharacterParameter::getCharacterName, parameter -> parameter));

            for (CharacterInfo info : characterInfos) {
                if (parameterMap.containsKey(info.getCharacterName())) {
                    CharacterParameter getParameter = parameterMap.get(info.getCharacterName());
                    parameterMap.remove(info.getCharacterName());

                    info.updateData(getParameter);
                    characterInfoRepository.save(info);
                } else {
                    characterInfoRepository.delete(info);
                }
            }

            if (!parameterMap.isEmpty()) {
                Set<Map.Entry<String, CharacterParameter>> entries = parameterMap.entrySet();
                List<CharacterInfo> list = entries.stream()
                        .map(entry -> entry.getValue().toCharacterInfo())
                        .map(characterInfo -> characterInfo.updateCharacter(character))
                        .toList();
                characterInfoRepository.saveAll(list);
            }
        }
        return character;
    }

    private List<CharacterParameter> get(String characterName) {
        String url = lostarkProperty.url() + "/characters/" + characterName + "/siblings";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", lostarkProperty.apiKey());

        ResponseEntity<List<CharacterParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional.ofNullable(response.getBody())
                .orElseGet(Collections::emptyList);
    }

}

package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.avatar.repository.AvatarRepository;
import com.didado.armory.domain.dto.LostarkProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final AvatarRepository avatarRepository;

    public void search(String characterName) {
        List<ArmoryAvatarParameter> avatarParameters = avatars(characterName);
        List<ArmoryAvatar> armoryAvatars = avatarRepository.findArmoryAvatarByCharacterName(characterName);

        if (armoryAvatars.isEmpty()) {
            log.debug("Armory Avatar Not Exist={}", characterName);
            List<ArmoryAvatar> convertAvatars = avatarParameters.stream()
                    .map(armoryAvatarParameter -> armoryAvatarParameter.toArmoryAvatar(characterName))
                    .toList();
            avatarRepository.saveAll(convertAvatars);
        } else {
            log.debug("Armory Avatar Exist={}", characterName);
            List<ArmoryAvatar> updateAvatars = armoryAvatars.stream()
                    .peek(armoryAvatar -> {
                        ArmoryAvatarParameter armoryAvatarParameter = avatarParameters.stream()
                                .filter(parameter -> parameter.getType().equals(armoryAvatar.getType()))
                                .findAny()
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아바타 타입입니다."));
                        armoryAvatar.updateData(armoryAvatarParameter, characterName);
                    })
                    .toList();

            avatarRepository.saveAll(updateAvatars);
        }
    }

    private List<ArmoryAvatarParameter> avatars(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/avatars";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<ArmoryAvatarParameter>> response = restTemplate.exchange(
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

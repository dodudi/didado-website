package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.Avatar;
import com.didado.armory.domain.avatar.domain.AvatarData;
import com.didado.armory.domain.avatar.dto.AvatarParameter;
import com.didado.armory.domain.avatar.exception.InvalidCharacterNameException;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import com.didado.armory.domain.avatar.repository.AvatarDataRepository;
import com.didado.armory.domain.avatar.repository.AvatarRepository;
import com.didado.armory.domain.dto.LostarkProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 로스트아크 API 를 이용해 데이터를 추가, 수정하는 클래스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarCollectionService implements AvatarCollection {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final AvatarDataRepository avatarDataRepository;
    private final AvatarRepository avatarRepository;


    /**
     * API 로 가져온 Avatar 정보를 저장하는 기능입니다.
     *
     * @param characterName 저장할 캐릭터의 이름입니다.
     */
    @Override
    public void save(String characterName) {
        List<AvatarParameter> newAvatars = getAvatars(characterName);


        //Save AvatarData
        AvatarData avatarData = new AvatarData(characterName);
        avatarDataRepository.save(avatarData);

        //Save Avatar
        List<Avatar> avatars = newAvatars.stream()
                .map(AvatarParameter::toArmoryAvatar)
                .toList();

        avatars.forEach(armoryAvatar -> {
            armoryAvatar.changeAvatarData(avatarData);
        });

        avatarRepository.saveAll(avatars);
    }


    /**
     * API 로 가져온 Avatar 정보를 수정하는 기능입니다.
     *
     * @param characterName 수정할 캐릭터의 이름입니다.
     */
    @Override
    @Modifying
    public void update(String characterName) {
        AvatarData oldAvatarData = avatarDataRepository.findByCharacterNameFetch(characterName)
                .orElseThrow(() -> new NotFoundAvatarException("캐릭터 이름에 Avatar 데이터가 없습니다.", characterName));

        List<Avatar> oldAvatars = oldAvatarData.getAvatars();

        List<AvatarParameter> newAvatars = getAvatars(characterName);
        Map<String, Avatar> newAvatarsMap = newAvatars.stream()
                .map(AvatarParameter::toArmoryAvatar)
                .collect(Collectors.toMap(Avatar::getKey, Function.identity()));

        //업데이트 진행이 안된 oldAvatar 가져오기
        List<Avatar> deleteAvatars = oldAvatars.stream()
                .map(oldAvatar -> {
                    String key = oldAvatar.getKey();
                    Avatar updateAvatar = newAvatarsMap.containsKey(key) ? newAvatarsMap.remove(key) : null;
                    if (updateAvatar != null)
                        oldAvatar.changeData(updateAvatar);

                    return updateAvatar == null ? oldAvatar : null;
                })
                .filter(Objects::nonNull)
                .toList();

        //새로운 데이터에 존재하지 않는 old 데이터 삭제
        deleteAvatars.forEach(avatar -> {
            avatar.deleteAvatarData();
            avatarRepository.delete(avatar);
        });

        //새로운 데이터 추가
        newAvatarsMap.forEach((s, avatar) -> {
            avatar.changeAvatarData(oldAvatarData);
            avatarRepository.save(avatar);
        });
    }

    private List<AvatarParameter> getAvatars(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/avatars";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<AvatarParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new InvalidCharacterNameException("존재 하는 캐릭터 이름이 아닙니다.", characterName));
    }
}

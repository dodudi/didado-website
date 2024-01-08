package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.Avatar;
import com.didado.armory.domain.avatar.domain.AvatarData;
import com.didado.armory.domain.avatar.dto.AvatarParameter;
import com.didado.armory.domain.avatar.repository.AvatarDataRepository;
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
     * Avatar 정보를 저장하는 기능입니다.
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

    @Override
    public void update(String characterName) {

    }

//    public void save(String characterName, List<ArmoryAvatarParameter> newAvatars) {
//        if (avatarInfoRepository.existsByCharacterName(characterName)) {
//            update(characterName, newAvatars);
//        } else {
//            //save
//            ArmoryAvatarInfo armoryAvatarInfo = ArmoryAvatarInfo.builder()
//                    .characterName(characterName)
//                    .build();
//
//            avatarInfoRepository.save(armoryAvatarInfo);
//
//            List<ArmoryAvatar> convertAvatars = newAvatars.stream()
//                    .map(ArmoryAvatarParameter::toArmoryAvatar)
//                    .toList();
//            convertAvatars.forEach(armoryAvatar -> armoryAvatar.changeAvatarInfo(armoryAvatarInfo));
//            avatarRepository.saveAll(convertAvatars);
//        }
//    }
//
//    private void update(String characterName, List<ArmoryAvatarParameter> newAvatars) {
//        ArmoryAvatarInfo avatarInfo = avatarInfoRepository.findByCharacterName(characterName)
//                .orElseThrow(() -> new NotFoundAvatarException("존재 하지 않는 캐릭터 이름입니다.", characterName));
//
//        List<ArmoryAvatar> oldAvatars = avatarRepository.findByArmoryAvatarInfoId(avatarInfo.getId());
//
//        //New Avatars Convert Map
//        Map<String, ArmoryAvatar> avatarMap = newAvatars.stream()
//                .map(ArmoryAvatarParameter::toArmoryAvatar)
//                .collect(Collectors.toMap(ArmoryAvatar::getType, armoryAvatar -> armoryAvatar));
//
//        //Update Old Avatars
//        oldAvatars.forEach(oldAvatar -> {
//            if (avatarMap.containsKey(oldAvatar.getType())) {
//                ArmoryAvatar newAvatar = avatarMap.get(oldAvatar.getType());
//                oldAvatar.changeData(newAvatar);
//            }
//        });
//    }

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
                .orElseGet(Collections::emptyList);
    }
}

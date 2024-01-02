package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.didado.armory.domain.avatar.domain.ArmoryAvatarInfo;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.avatar.dto.AvatarInfoParameter;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import com.didado.armory.domain.avatar.repository.AvatarInfoRepository;
import com.didado.armory.domain.avatar.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final AvatarInfoRepository avatarInfoRepository;

    public AvatarInfoParameter search(String characterName) {
        ArmoryAvatarInfo avatarInfo = avatarInfoRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new NotFoundAvatarException("존재 하지 않는 캐릭터 이름입니다.", characterName));

        List<ArmoryAvatarParameter> convertAvatars = avatarRepository.findByArmoryAvatarInfoId(avatarInfo.getId()).stream()
                .map(ArmoryAvatarParameter::new)
                .toList();

        return new AvatarInfoParameter(avatarInfo.getCharacterName(), convertAvatars);
    }
}

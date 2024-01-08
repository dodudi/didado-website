package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.AvatarData;
import com.didado.armory.domain.avatar.dto.AvatarParameter;
import com.didado.armory.domain.avatar.dto.AvatarDataParameter;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import com.didado.armory.domain.avatar.repository.AvatarDataRepository;
import com.didado.armory.domain.avatar.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final AvatarDataRepository avatarDataRepository;

    public AvatarDataParameter search(String characterName) {
        AvatarData avatarData = avatarDataRepository.findByCharacterNameFetch(characterName)
                .orElseThrow(() -> new NotFoundAvatarException("존재 하지 않는 캐릭터 이름입니다.", characterName));

        List<AvatarParameter> convertAvatars = avatarData.getAvatars().stream()
                .map(AvatarParameter::new)
                .toList();

        return new AvatarDataParameter(avatarData.getCharacterName(), convertAvatars);
    }
}

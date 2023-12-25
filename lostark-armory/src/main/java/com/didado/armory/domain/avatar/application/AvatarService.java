package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
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

    public List<ArmoryAvatarParameter> search(String characterName){
        List<ArmoryAvatar> avatars = avatarRepository.findArmoryAvatarByCharacterName(characterName);
        return avatars.stream()
                .map(ArmoryAvatarParameter::new)
                .toList();
    }
}

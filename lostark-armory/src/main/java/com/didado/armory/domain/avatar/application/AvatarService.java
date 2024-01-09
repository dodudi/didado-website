package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.dto.AvatarDataParameter;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarCollectionService avatarCollectionService;
    private final AvatarQueryService avatarQueryService;

    public AvatarDataParameter search(String characterName) {
        try {
            return avatarQueryService.search(characterName);
        } catch (NotFoundAvatarException e) {
            avatarCollectionService.save(characterName);
            return avatarQueryService.search(characterName);
        }
    }
}

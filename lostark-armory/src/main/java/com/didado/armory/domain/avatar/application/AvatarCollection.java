package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.dto.AvatarParameter;

import java.util.List;

public interface AvatarCollection {

    void save(String characterName);

    void update(String characterName);
}

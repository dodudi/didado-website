package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.dto.AvatarDataParameter;
import com.didado.armory.domain.avatar.dto.AvatarParameter;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AvatarQueryServiceTest {

    @Autowired
    private AvatarQueryService avatarQueryService;

    @Autowired
    private AvatarCollection avatarCollection;

    @Test
    void search() {
        String characterName = "디다도두";

        Assertions.assertThatThrownBy(() -> avatarQueryService.search(characterName))
                .isInstanceOf(NotFoundAvatarException.class);

        avatarCollection.save(characterName);
        AvatarDataParameter avatarData = avatarQueryService.search(characterName);

        Boolean first = avatarData.getAvatarParameters().stream().map(AvatarParameter::getIsSet)
                .filter(isSet -> isSet)
                .limit(1)
                .findFirst()
                .orElseGet(() -> false);

        Assertions.assertThat(avatarData.getAvatarParameters().size()).isEqualTo(first ? 3 : 9);
        log.info("{}", avatarData);
    }
}
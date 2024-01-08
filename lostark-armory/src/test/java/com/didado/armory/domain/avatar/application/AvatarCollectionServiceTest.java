package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.AvatarData;
import com.didado.armory.domain.avatar.repository.AvatarDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AvatarCollectionServiceTest {
    @Autowired
    private AvatarCollection avatarCollection;

    @Autowired
    private AvatarDataRepository avatarDataRepository;

    @Test
    void save() {
        avatarCollection.save("Nelip");

        List<AvatarData> avatarDatas = avatarDataRepository.findByCharacterName("Nelip")
                .stream().peek(avatarData -> log.info("{}", avatarData))
                .toList();

        Assertions.assertThat(avatarDatas.size()).isEqualTo(1);
    }
}
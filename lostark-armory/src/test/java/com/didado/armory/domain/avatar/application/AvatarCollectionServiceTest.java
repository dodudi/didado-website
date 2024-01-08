package com.didado.armory.domain.avatar.application;

import com.didado.armory.domain.avatar.domain.Avatar;
import com.didado.armory.domain.avatar.domain.AvatarData;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import com.didado.armory.domain.avatar.repository.AvatarDataRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
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
    private EntityManager em;

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

    @Nested
    class Update {

        @Test
        void success() {

            avatarCollection.save("Nelip");
            AvatarData avatarData = avatarDataRepository.findByCharacterNameFetch("Nelip")
                    .orElseThrow(() -> new NotFoundAvatarException("", "Nelip"));
            List<Avatar> avatars = avatarData.getAvatars();
            avatars.forEach(avatar -> avatar.changeData(new Avatar("1", "1", "1", "1", true, false, "")));
            em.flush();
            em.clear();
            log.info("저장 완료");

            avatarCollection.update("Nelip");

            AvatarData avatarData3 = avatarDataRepository.findByCharacterNameFetch("Nelip")
                    .orElseThrow(() -> new NotFoundAvatarException("", "Nelip"));

            Assertions.assertThat(avatarData3.getAvatars().size()).isEqualTo(9);
            log.info("{}", avatarData3);
            log.info("업데이트 완료");
        }

        @Test
        void throwNotFoundAvatarException() {
            AbstractThrowableAssert<?, ? extends Throwable> throwable = Assertions.assertThatThrownBy(() -> avatarCollection.update("Nelip"))
                    .isInstanceOf(NotFoundAvatarException.class)
                    .as("Not Found Avatar Exception 이 발생해야한다.")
                    .withFailMessage(() -> "Not Found Avatar Exception 이 발생해야한다.");

        }
    }

}
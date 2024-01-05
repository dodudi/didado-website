package com.didado.armory.domain.avatar.api;

import com.didado.armory.domain.avatar.application.AvatarService;
import com.didado.armory.domain.avatar.domain.ArmoryAvatarInfo;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.avatar.dto.AvatarInfoParameter;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @GetMapping("/lostark/armory/{characterName}/avatars")
    public ResponseEntity<AvatarInfoParameter> avatars(@PathVariable("characterName") String characterName) {
        return ResponseEntity.ok(avatarService.search(characterName));
    }

    @ExceptionHandler(NotFoundAvatarException.class)
    public ResponseEntity<AvatarInfoParameter> avatar(NotFoundAvatarException e) {

        AvatarInfoParameter result = WebClient.create()
                .post()
                .uri("http://localhost:8080/lostark/armory/core")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new CoreSaveParameter(ArmoryType.AVATAR, e.getCharacterName())))
                .retrieve()
                .bodyToMono(AvatarInfoParameter.class)
                .block();

        return ResponseEntity.ok(result);
    }

}

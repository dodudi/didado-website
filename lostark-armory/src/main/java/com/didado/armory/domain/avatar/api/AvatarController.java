package com.didado.armory.domain.avatar.api;

import com.didado.armory.domain.avatar.application.AvatarCollectionService;
import com.didado.armory.domain.avatar.application.AvatarService;
import com.didado.armory.domain.avatar.dto.AvatarDataParameter;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;
    private final AvatarCollectionService avatarCollectService;

    @GetMapping("/lostark/armory/{characterName}/avatars")
    public ResponseEntity<AvatarDataParameter> avatars(@PathVariable("characterName") String characterName) {
        return ResponseEntity.ok(avatarService.search(characterName));
    }

    @ExceptionHandler(NotFoundAvatarException.class)
    public ResponseEntity<AvatarDataParameter> avatar(NotFoundAvatarException e) {
        avatarCollectService.save(e.getCharacterName());
        return ResponseEntity.ok(avatarService.search(e.getCharacterName()));
//        AvatarInfoParameter result = WebClient.create()
//                .post()
//                .uri("http://localhost:8080/lostark/armory/core")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(new CoreSaveParameter(ArmoryType.AVATAR, e.getCharacterName())))
//                .retrieve()
//                .bodyToMono(AvatarInfoParameter.class)
//                .block();
//
//        return ResponseEntity.ok(result);
    }

}

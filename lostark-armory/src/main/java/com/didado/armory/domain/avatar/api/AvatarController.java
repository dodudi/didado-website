package com.didado.armory.domain.avatar.api;

import com.didado.armory.domain.avatar.application.AvatarCollectionService;
import com.didado.armory.domain.avatar.application.AvatarService;
import com.didado.armory.domain.avatar.dto.AvatarDataParameter;
import com.didado.armory.domain.avatar.dto.AvatarResponse;
import com.didado.armory.domain.avatar.exception.InvalidCharacterNameException;
import com.didado.armory.domain.avatar.exception.NotFoundAvatarException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AvatarResponse> avatars(@PathVariable("characterName") String characterName) {
        AvatarResponse response = new AvatarResponse(200, "Success", avatarService.search(characterName));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(NotFoundAvatarException.class)
    public ResponseEntity<AvatarResponse> avatar(NotFoundAvatarException e) {
        try {
            avatarCollectService.save(e.getCharacterName());
            AvatarResponse response = new AvatarResponse(200, "Success", avatarService.search(e.getCharacterName()));
            return ResponseEntity.ok(response);
        } catch (InvalidCharacterNameException e1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AvatarResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e1.getMessage(), null));
        }
    }
}

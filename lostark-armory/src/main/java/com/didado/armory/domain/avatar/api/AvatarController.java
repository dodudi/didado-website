package com.didado.armory.domain.avatar.api;

import com.didado.armory.domain.avatar.application.AvatarCollectionService;
import com.didado.armory.domain.avatar.application.AvatarQueryService;
import com.didado.armory.domain.avatar.application.AvatarService;
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

    private AvatarService avatarService;

    @GetMapping("/lostark/armory/{characterName}/avatars")
    public ResponseEntity<AvatarResponse> avatars(@PathVariable("characterName") String characterName) {
        AvatarResponse response = new AvatarResponse(200, "Success", avatarService.search(characterName));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(InvalidCharacterNameException.class)
    public ResponseEntity<AvatarResponse> avatar(InvalidCharacterNameException e) {
        AvatarResponse response = new AvatarResponse(404, e.getMessage(), null);
        return ResponseEntity.ok(response);
    }
}

package com.didado.armory.domain.avatar.api;

import com.didado.armory.domain.avatar.application.AvatarService;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @GetMapping("/lostark/armory/{characterName}/avatars")
    public ResponseEntity<List<ArmoryAvatarParameter>> avatars(@PathVariable("characterName") String characterName){
        return ResponseEntity.ok(avatarService.search(characterName));
    }
}

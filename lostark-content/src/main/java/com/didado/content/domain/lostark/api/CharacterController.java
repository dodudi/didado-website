package com.didado.content.domain.lostark.api;

import com.didado.content.domain.lostark.application.CharacterServiceImpl;
import com.didado.content.domain.lostark.dto.character.CharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterServiceImpl characterService;

    @GetMapping("/lostark/{username}/characters")
    public ResponseEntity<CharacterResponse> searches(@PathVariable(value = "username") String username) {
        return ResponseEntity.ok(characterService.searches(username));
    }
}

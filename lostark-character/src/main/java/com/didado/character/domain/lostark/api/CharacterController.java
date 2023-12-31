package com.didado.character.domain.lostark.api;

import com.didado.character.domain.lostark.application.CharacterSearchService;
import com.didado.character.domain.lostark.domain.Character;
import com.didado.character.domain.lostark.dto.CharacterParameter;
import com.didado.character.domain.lostark.dto.CharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterSearchService searchService;

    @GetMapping("/lostark/{characterName}/characters")
    public ResponseEntity<CharacterResponse> test(@PathVariable String characterName) {
        return ResponseEntity.ok(new CharacterResponse("캐릭터 조회에 성공했습니다.", 200, searchService.search(characterName)));
    }
}

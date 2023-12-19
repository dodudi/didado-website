package com.didado.character.domain.lostark.api;

import com.didado.character.domain.lostark.application.CharacterSearchService;
import com.didado.character.domain.lostark.domain.Character;
import com.didado.character.domain.lostark.dto.CharacterParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterSearchService searchService;

    @GetMapping("/lostark/{characterName}/characters")
    public List<CharacterParameter> test(@PathVariable String characterName) {
        return searchService.search(characterName);
    }
}

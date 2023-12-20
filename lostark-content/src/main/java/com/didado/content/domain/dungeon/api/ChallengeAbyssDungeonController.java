package com.didado.content.domain.dungeon.api;

import com.didado.content.domain.dungeon.dto.ChallengeAbyssDungeonResponse;
import com.didado.content.domain.dungeon.application.ChallengeAbyssDungeonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeAbyssDungeonController {
    private ChallengeAbyssDungeonServiceImpl abyssDungeonService;

    @GetMapping("/lostark/contents/challenge-abyss-dungeons")
    public ResponseEntity<ChallengeAbyssDungeonResponse> abyssDungeons() {
        return ResponseEntity.ok(abyssDungeonService.abyssDungeons());
    }
}

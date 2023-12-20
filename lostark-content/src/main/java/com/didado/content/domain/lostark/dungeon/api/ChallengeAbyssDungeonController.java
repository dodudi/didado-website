package com.didado.content.domain.lostark.dungeon.api;

import com.didado.content.domain.lostark.application.ChallengeAbyssDungeonServiceImpl;
import com.didado.content.domain.lostark.dungeon.dto.ChallengeAbyssDungeonResponse;
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

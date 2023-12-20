package com.didado.content.domain.lostark.raid.api;

import com.didado.content.domain.lostark.raid.application.ChallengeGuardianRaidService;
import com.didado.content.domain.lostark.raid.dto.ChallengeGuardianRaidResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeGuardianRaidController {
    private final ChallengeGuardianRaidService challengeGuardianRaidService;

    @GetMapping("/lostark/contents/challenge-guardian-raids")
    public ResponseEntity<ChallengeGuardianRaidResponse> guardianRaids() {
        return ResponseEntity.ok(challengeGuardianRaidService.guardianRaids());
    }
}

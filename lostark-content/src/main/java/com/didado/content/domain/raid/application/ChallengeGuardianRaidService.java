package com.didado.content.domain.raid.application;

import com.didado.content.config.LostarkProperty;
import com.didado.content.domain.raid.dto.ChallengeGuardianRaidResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeGuardianRaidService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public ChallengeGuardianRaidResponse guardianRaids() {
        String url = property.url() + "/gamecontents/challenge-guardian-raids";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<ChallengeGuardianRaidResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ChallengeGuardianRaidResponse("도전 가디언 레이드 조회 성공", 200, response.getBody());
    }
}

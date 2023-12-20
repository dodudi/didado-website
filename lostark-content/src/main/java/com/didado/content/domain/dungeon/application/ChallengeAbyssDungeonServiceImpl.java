package com.didado.content.domain.dungeon.application;

import com.didado.content.config.LostarkProperty;
import com.didado.content.domain.dungeon.dto.ChallengeAbyssDungeonParameter;
import com.didado.content.domain.dungeon.dto.ChallengeAbyssDungeonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeAbyssDungeonServiceImpl {

    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public ChallengeAbyssDungeonResponse abyssDungeons() {
        String url = property.url() + "/gamecontents/challenge-abyss-dungeons";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<List<ChallengeAbyssDungeonParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ChallengeAbyssDungeonResponse("도전 어비스 던전 정보 조회 성공.", 200, response.getBody());
    }
}

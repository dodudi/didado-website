package com.website.didado.domain.lostark.application;

import com.website.didado.domain.lostark.domain.LostarkProperty;
import com.website.didado.domain.lostark.dto.character.Character;
import com.website.didado.domain.lostark.dto.character.CharacterResponse;
import com.website.didado.domain.lostark.dto.content.ChallengeAbyssDungeon;
import com.website.didado.domain.lostark.dto.content.ContentResponse;
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
public class ContentServiceImpl {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public ContentResponse abyssDungeons() {
        String url = property.url() + "/gamecontents/challenge-abyss-dungeons";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<List<ChallengeAbyssDungeon>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ContentResponse("도전 어비스 던전 정보 조회 성공.", 200, response.getBody());
    }
}

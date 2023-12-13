package com.didado.content.domain.lostark.application;

import com.didado.content.domain.lostark.domain.LostarkProperty;
import com.didado.content.domain.lostark.dto.character.CharacterResponse;
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
public class CharacterServiceImpl {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public CharacterResponse searches(String username) {
        String url = property.url() + "/characters/" + username + "/siblings";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<List<Character>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {}
        );

        return new CharacterResponse("캐릭터 정보 조회에 성공.", 200, response.getBody());
    }
}

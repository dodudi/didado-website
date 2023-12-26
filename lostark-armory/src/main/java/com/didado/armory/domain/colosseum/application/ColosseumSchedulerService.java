package com.didado.armory.domain.colosseum.application;

import com.didado.armory.domain.colosseum.domain.ColosseumInfo;
import com.didado.armory.domain.colosseum.dto.ColosseumInfoParameter;
import com.didado.armory.domain.dto.LostarkProperty;
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
public class ColosseumSchedulerService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public void search(String characterName) {
        ColosseumInfoParameter colosseums = getColosseums(characterName);
        log.debug("{}", colosseums);
    }

    private ColosseumInfoParameter getColosseums(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/colosseums";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ColosseumInfoParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();

    }
}

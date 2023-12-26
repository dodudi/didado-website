package com.didado.armory.domain.collectible.application;

import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.collectible.dto.CollectiblePointParameter;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectibleSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    public void search(String characterName) {
        List<CollectibleParameter> collectibleParameter = getCollectibleParameter(characterName);
        log.debug("{}", collectibleParameter);
    }

    private List<CollectibleParameter> getCollectibleParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/collectibles";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<CollectibleParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional.ofNullable(response.getBody())
                .orElseGet(Collections::emptyList);
    }
}

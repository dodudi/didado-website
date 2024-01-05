package com.didado.armory.domain.gem.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.gem.dto.ArmoryGemParameter;
import lombok.Getter;
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
public class GemSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    public void search(String characterName) {
        ArmoryGemParameter gems = gems(characterName);

        log.debug("Gem={}", gems.getGems());
        log.debug("Gem Effect={}", gems.getEffects());
    }

    private ArmoryGemParameter gems(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/gems";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryGemParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}

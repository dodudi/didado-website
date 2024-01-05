package com.didado.armory.domain.engraving.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.engraving.dto.ArmoryEngravingParameter;
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
public class EngravingSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    public void search(String characterName) {
        ArmoryEngravingParameter engravingParameter = getEngravingParameter(characterName);
        log.debug("{}", engravingParameter.getEngravings());
        log.debug("{}", engravingParameter.getEffects());
    }

    private ArmoryEngravingParameter getEngravingParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/engravings";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryEngravingParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}

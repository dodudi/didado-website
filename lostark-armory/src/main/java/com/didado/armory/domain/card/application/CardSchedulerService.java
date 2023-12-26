package com.didado.armory.domain.card.application;

import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.equipment.repository.EquipmentRepository;
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
public class CardSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    public void search(String characterName) {
        ArmoryCardParameter cardParameter = getCardParameter(characterName);
        log.debug("Card={}", cardParameter.getCards());
        log.debug("Card Effect={}", cardParameter.getEffects());
    }

    private ArmoryCardParameter getCardParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/cards";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryCardParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}

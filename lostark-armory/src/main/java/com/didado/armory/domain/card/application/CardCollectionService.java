package com.didado.armory.domain.card.application;

import com.didado.armory.domain.card.dto.ArmoryCardParameter;
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
public class CardCollectionService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    /**
     * API 에서 데이터를 가져와 저장하는 기능
     *
     * @param characterName 저장 할 캐릭터 이름
     */
    public void save(String characterName) {
        ArmoryCardParameter cardParameter = getCardParameter(characterName);

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

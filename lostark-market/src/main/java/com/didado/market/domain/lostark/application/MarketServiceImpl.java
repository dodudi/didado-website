package com.didado.market.domain.lostark.application;

import com.didado.market.domain.lostark.domain.LostarkProperty;
import com.didado.market.domain.lostark.dto.*;
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
public class MarketServiceImpl {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public MarketResponse options() {
        String url = property.url() + "/markets/options";

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<MarketOption> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), MarketOption.class);
        log.debug("{}", response.getBody());

        return new MarketResponse("Market 옵션 검색 성공", 200, response.getBody());
    }

    public MarketResponse items(MarketParameter parameter) {
        String url = property.url() + "/markets/items";

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<MarketList> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(parameter, headers), MarketList.class);
        log.debug("{}", response.getBody());

        return new MarketResponse("Market 검색 성공", 200, response.getBody());
    }

    public MarketResponse items(Integer itemId) {
        String url = property.url() + "/markets/items/" + itemId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<List<MarketItemStats>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
        log.debug("{}", response.getBody());

        return new MarketResponse("Market 검색 성공", 200, response.getBody());
    }
}

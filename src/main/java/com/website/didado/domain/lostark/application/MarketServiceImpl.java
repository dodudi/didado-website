package com.website.didado.domain.lostark.application;

import com.website.didado.domain.lostark.domain.LostarkProperty;
import com.website.didado.domain.lostark.dto.market.MarketOption;
import com.website.didado.domain.lostark.dto.market.MarketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}

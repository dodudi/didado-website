package com.website.didado.domain.lostark.application;

import com.website.didado.domain.lostark.domain.LostarkProperty;
import com.website.didado.domain.lostark.dto.auction.AuctionOption;
import com.website.didado.domain.lostark.dto.auction.AuctionResponse;
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
public class AuctionServiceImpl {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public AuctionResponse options() {
        String url = property.url() + "/auctions/options";

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<AuctionOption> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), AuctionOption.class);
        log.debug("{}", response.getBody());

        return new AuctionResponse("경매장 상세 조건 조회 성공", 200, response.getBody());
    }
}

package com.didado.auction.lostark.application;

import com.didado.auction.lostark.domain.LostarkProperty;
import com.didado.auction.lostark.dto.auction.Auction;
import com.didado.auction.lostark.dto.auction.AuctionOption;
import com.didado.auction.lostark.dto.auction.AuctionParameter;
import com.didado.auction.lostark.dto.auction.AuctionResponse;
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

    public AuctionResponse items(AuctionParameter parameter) {
        log.debug("{}", parameter);
        String url = property.url() + "/auctions/items";

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<Auction> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(parameter, headers), Auction.class);
        log.debug("{}", response.getBody());

        return new AuctionResponse("경매장 상세 조건 조회 성공", 200, response.getBody());
    }
}

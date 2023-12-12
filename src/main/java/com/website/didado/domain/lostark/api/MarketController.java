package com.website.didado.domain.lostark.api;

import com.website.didado.domain.lostark.application.MarketServiceImpl;
import com.website.didado.domain.lostark.dto.market.MarketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketServiceImpl marketService;

    @GetMapping("/lostark/markets/options")
    public ResponseEntity<MarketResponse> options() {
        return ResponseEntity.ok(marketService.options());
    }
}

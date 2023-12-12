package com.website.didado.domain.lostark.api;

import com.website.didado.domain.lostark.application.MarketServiceImpl;
import com.website.didado.domain.lostark.dto.market.MarketParameter;
import com.website.didado.domain.lostark.dto.market.MarketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MarketController {
    private final MarketServiceImpl marketService;

    @GetMapping("/lostark/markets/options")
    public ResponseEntity<MarketResponse> options() {
        return ResponseEntity.ok(marketService.options());
    }

    @PostMapping("/lostark/markets/items")
    public ResponseEntity<MarketResponse> items(@RequestBody MarketParameter parameter) {
        return ResponseEntity.ok(marketService.items(parameter));
    }
}

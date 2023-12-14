package com.didado.market.domain.lostark.api;

import com.didado.market.domain.lostark.application.MarketServiceImpl;
import com.didado.market.domain.lostark.dto.MarketParameter;
import com.didado.market.domain.lostark.dto.MarketResponse;
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

    @GetMapping("/lostark/markets/items/{itemId}")
    public ResponseEntity<MarketResponse> items(@PathVariable Integer itemId) {
        return ResponseEntity.ok(marketService.items(itemId));
    }
}

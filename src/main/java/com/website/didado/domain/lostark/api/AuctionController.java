package com.website.didado.domain.lostark.api;

import com.website.didado.domain.lostark.application.AuctionServiceImpl;
import com.website.didado.domain.lostark.dto.auction.AuctionParameter;
import com.website.didado.domain.lostark.dto.auction.AuctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionServiceImpl auctionService;

    @GetMapping("/lostark/auction/options")
    public ResponseEntity<AuctionResponse> options() {
        return ResponseEntity.ok(auctionService.options());
    }

    @PostMapping("/lostark/auction/items")
    public ResponseEntity<AuctionResponse> items(@RequestBody AuctionParameter parameter) {
        return ResponseEntity.ok(auctionService.items(parameter));
    }
}

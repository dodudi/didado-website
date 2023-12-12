package com.website.didado.domain.lostark.api;

import com.website.didado.domain.lostark.application.ContentServiceImpl;
import com.website.didado.domain.lostark.dto.content.ContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ContentServiceImpl contentService;

    @GetMapping("/lostark/contents/challenge-abyss-dungeons")
    public ResponseEntity<ContentResponse> abyssDungeons() {
        return ResponseEntity.ok(contentService.abyssDungeons());
    }

    @GetMapping("/lostark/contents/challenge-guardian-raids")
    public ResponseEntity<ContentResponse> guardianRaids() {
        return ResponseEntity.ok(contentService.guardianRaids());
    }

    @GetMapping("/lostark/contents/calendar")
    public ResponseEntity<ContentResponse> calendar() {
        return ResponseEntity.ok(contentService.calendar());
    }
}

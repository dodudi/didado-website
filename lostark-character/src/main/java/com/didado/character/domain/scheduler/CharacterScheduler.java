package com.didado.character.domain.scheduler;

import com.didado.character.domain.lostark.application.CharacterApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CharacterScheduler {
    @Value("${server.port}")
    private int port;

    private final CharacterApiService characterApiService;

    @Scheduled(cron = "0/50 * * * * MON-FRI")
    public void calender() {
        characterApiService.searches();
    }
}

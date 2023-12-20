package com.didado.content.domain.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class LostarkScheduler {

//    @Value("${server.port}")
//    private int port;
//    private final RestTemplate restTemplate;
//
//    @Scheduled(cron = "0 0/30 * * * *")
//    public void calender() {
//        restTemplate.exchange("http://localhost:" + port + "/lostark/contents/calendar", HttpMethod.GET, null, Void.class);
//        LocalDateTime now = LocalDateTime.now();
//        log.info("Calender Scheduled = {}", now);
//    }
}

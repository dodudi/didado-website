package com.didado.content.schedule;

import com.didado.content.domain.calendar.application.CalendarApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class LostarkScheduler {

    @Value("${server.port}")
    private int port;
    private final RestTemplate restTemplate;

    private final CalendarApiService service;

    @Scheduled(cron = "* 30 10 * * 3")
    public void calender() {
        service.searches();
    }
}

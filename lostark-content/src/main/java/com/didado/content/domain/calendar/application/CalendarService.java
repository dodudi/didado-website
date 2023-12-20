package com.didado.content.domain.calendar.application;

import com.didado.content.domain.calendar.dto.CalendarResponse;
import com.didado.content.domain.calendar.dto.ContentsCalendarParameter;
import com.didado.content.config.LostarkProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public CalendarResponse calendar() {
        String url = property.url() + "/gamecontents/calendar";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<List<ContentsCalendarParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new CalendarResponse("캘린더 정보 조회 성공.", 200, response.getBody());
    }
}

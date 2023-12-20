package com.didado.content.domain.calendar.application;

import com.didado.content.config.LostarkProperty;
import com.didado.content.domain.calendar.domain.Calendar;
import com.didado.content.domain.calendar.domain.CalendarRewardItem;
import com.didado.content.domain.calendar.domain.CalendarTime;
import com.didado.content.domain.calendar.dto.CalendarParameter;
import com.didado.content.domain.calendar.dto.CalendarRewardParameter;
import com.didado.content.domain.calendar.repository.CalendarRewardItemRepository;
import com.didado.content.domain.calendar.repository.CalendarTimeRepository;
import com.didado.content.domain.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarApiService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    private final CalendarRepository calendarRepository;
    private final CalendarTimeRepository calendarTimeRepository;
    private final CalendarRewardItemRepository calendarRewardItemRepository;

    public void searches() {
        List<CalendarParameter> calenderParameters = getCalenders();
        Calendar calendar = new Calendar();
        calendarRepository.save(calendar);

        for (CalendarParameter calendarParameter : calenderParameters) {
            List<String> startTimes = calendarParameter.getStartTimes();
            List<CalendarRewardParameter> rewardItems = calendarParameter.getRewardItems();

            //Times
            List<CalendarTime> convertTimes = startTimes.stream()
                    .map(CalendarTime::new)
                    .map(calendarTime -> calendarTime.updateCalendar(calendar))
                    .toList();
            calendarTimeRepository.saveAll(convertTimes);

            //Items
            List<CalendarRewardItem> convertItems = rewardItems.stream()
                    .map(calendarRewardParameter -> calendarRewardParameter.toCalendarRewardItem(calendar))
                    .toList();
            calendarRewardItemRepository.saveAll(convertItems);
        }

        log.debug("{}", calendar);
    }


    private List<CalendarParameter> getCalenders() {
        String url = property.url() + "/gamecontents/calendar";
        log.debug("Lostark Calendar Url={}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());

        ResponseEntity<List<CalendarParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional
                .ofNullable(response.getBody())
                .orElseGet(Collections::emptyList);
    }
}

package com.didado.content.domain.calendar.api;


import com.didado.content.domain.calendar.application.CalendarService;
import com.didado.content.domain.calendar.dto.CalendarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/lostark/contents/calendar")
    public ResponseEntity<CalendarResponse> calendar() {
        return null;
//        return ResponseEntity.ok(calendarService.calendar());
    }
}

package com.didado.content.domain.calendar.repository;

import com.didado.content.domain.calendar.domain.CalendarTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarTimeRepository extends JpaRepository<CalendarTime, Long> {
}

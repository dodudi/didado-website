package com.didado.content.domain.calendar.repository;

import com.didado.content.domain.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}

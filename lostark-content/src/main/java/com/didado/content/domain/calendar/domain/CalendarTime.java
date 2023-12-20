package com.didado.content.domain.calendar.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString(exclude = "calendar")
@EntityListeners(AuditingEntityListener.class)
public class CalendarTime {
    @Id
    @GeneratedValue
    @Column(name = "calendar_time_id")
    private Long id;
    private String startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    protected CalendarTime() {
    }

    public CalendarTime(String startTime) {
        this.startTime = startTime;
    }

    public CalendarTime updateCalendar(Calendar calendar) {
        this.calendar = calendar;
        calendar.getStartTimes().add(this);
        return this;
    }
}

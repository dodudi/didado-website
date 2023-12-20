package com.didado.content.domain.calendar.domain;

import jakarta.persistence.*;
import lombok.Builder;
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
public class CalendarRewardItem {
    @Id
    @GeneratedValue
    @Column(name = "calendar_reward_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    private String name;
    private String icon;
    private String grade;
    //    private List<String> startTimes;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    protected CalendarRewardItem() {
    }

    @Builder
    public CalendarRewardItem(String name, String icon, String grade) {
        this.name = name;
        this.icon = icon;
        this.grade = grade;
    }

    public CalendarRewardItem updateCalendar(Calendar calendar) {
        this.calendar = calendar;
        calendar.getRewardItems().add(this);
        return this;
    }
}

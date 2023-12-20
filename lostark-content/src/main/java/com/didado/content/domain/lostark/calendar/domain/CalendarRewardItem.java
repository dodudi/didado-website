package com.didado.content.domain.lostark.calendar.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}

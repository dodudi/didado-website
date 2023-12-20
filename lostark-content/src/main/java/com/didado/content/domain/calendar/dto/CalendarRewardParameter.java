package com.didado.content.domain.calendar.dto;

import com.didado.content.domain.calendar.domain.Calendar;
import com.didado.content.domain.calendar.domain.CalendarRewardItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CalendarRewardParameter {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "StartTimes")
    private List<String> startTimes;

    public CalendarRewardItem toCalendarRewardItem(Calendar calendar) {
        CalendarRewardItem build = CalendarRewardItem.builder()
                .name(this.name)
                .icon(this.icon)
                .grade(this.grade)
                .build();
        return build.updateCalendar(calendar);
    }
}

package com.didado.content.domain.calendar.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Calendar {
    @Id
    @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;
    private String categoryName;
    private String contentsName;
    private String contentsIcon;
    private Integer minItemLevel;
//    private List<String> startTimes;
    private String location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
    private List<CalendarRewardItem> rewardItems;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}

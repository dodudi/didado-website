package com.didado.content.domain.calendar.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Calendar {
    @Id
    @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;
    private String categoryName;
    private String contentsName;
    private String contentsIcon;
    private Integer minItemLevel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
    private List<CalendarTime> startTimes = new ArrayList<>();
    private String location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
    private List<CalendarRewardItem> rewardItems = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}

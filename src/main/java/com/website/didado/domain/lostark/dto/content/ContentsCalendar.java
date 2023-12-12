package com.website.didado.domain.lostark.dto.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ContentsCalendar {

    @JsonProperty(value = "CategoryName")
    private String categoryName;

    @JsonProperty(value = "ContentsName")
    private String contentsName;

    @JsonProperty(value = "ContentsIcon")
    private String contentsIcon;

    @JsonProperty(value = "MinItemLevel")
    private Integer minItemLevel;

    @JsonProperty(value = "StartTimes")
    private List<String> startTimes;

    @JsonProperty(value = "Location")
    private String location;

    @JsonProperty(value = "RewardItems")
    private List<RewardItem> rewardItems;
}

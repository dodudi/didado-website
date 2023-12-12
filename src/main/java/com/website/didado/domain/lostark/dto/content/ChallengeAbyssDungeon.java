package com.website.didado.domain.lostark.dto.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ChallengeAbyssDungeon {

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "MinCharacterLevel")
    private Integer minCharacterLevel;

    @JsonProperty(value = "MinItemLevel")
    private Integer minItemLevel;

    @JsonProperty(value = "AreaName")
    private String areaName;

    @JsonProperty(value = "StartTime")
    private String startTime;

    @JsonProperty(value = "EndTime")
    private String endTime;

    @JsonProperty(value = "Image")
    private String image;

    @JsonProperty(value = "RewardItems")
    private List<RewardItem> rewardItems;
}

package com.didado.content.domain.lostark.dungeon.dto;

import com.didado.content.domain.lostark.common.dto.RewardItemParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ChallengeAbyssDungeonParameter {

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
    private List<RewardItemParameter> rewardItems;
}

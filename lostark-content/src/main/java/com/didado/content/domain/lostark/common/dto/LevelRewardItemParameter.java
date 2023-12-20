package com.didado.content.domain.lostark.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class LevelRewardItemParameter {

    @JsonProperty(value = "ExpeditionItemLevel")
    private Integer expeditionItemLevel;

    @JsonProperty(value = "Items")
    private List<RewardItemParameter> items;
}

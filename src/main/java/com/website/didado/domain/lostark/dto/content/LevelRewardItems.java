package com.website.didado.domain.lostark.dto.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class LevelRewardItems {

    @JsonProperty(value = "ExpeditionItemLevel")
    private Integer expeditionItemLevel;

    @JsonProperty(value = "Items")
    private List<RewardItem> items;
}

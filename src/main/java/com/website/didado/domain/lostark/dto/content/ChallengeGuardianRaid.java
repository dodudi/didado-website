package com.website.didado.domain.lostark.dto.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ChallengeGuardianRaid {

    @JsonProperty(value = "Raids")
    private List<GuardianRaid> Raids;

    @JsonProperty(value = "RewardItems")
    private List<LevelRewardItems> RewardItems;
}

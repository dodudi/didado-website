package com.didado.content.domain.raid.dto;

import com.didado.content.domain.common.dto.LevelRewardItemParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ChallengeGuardianRaidParameter {

    @JsonProperty(value = "Raids")
    private List<GuardianRaidParameter> Raids;

    @JsonProperty(value = "RewardItems")
    private List<LevelRewardItemParameter> RewardItems;
}

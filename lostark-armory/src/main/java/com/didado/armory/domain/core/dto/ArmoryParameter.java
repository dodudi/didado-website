package com.didado.armory.domain.core.dto;

import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.didado.armory.domain.gem.dto.ArmoryGemParameter;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.colosseum.dto.ColosseumInfoParameter;
import com.didado.armory.domain.engraving.dto.ArmoryEngravingParameter;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryParameter {
    @JsonProperty(value = "ArmoryProfile")
    private ArmoryProfileParameter armoryProfile;

    @JsonProperty(value = "ArmoryEquipment")
    private List<ArmoryEquipmentParameter> armoryEquipment;

    @JsonProperty(value = "ArmoryAvatars")
    private List<ArmoryAvatarParameter> armoryAvatars;

    @JsonProperty(value = "ArmorySkills")
    private List<ArmorySkillParameter> armorySkills;

    @JsonProperty(value = "ArmoryEngraving")
    private ArmoryEngravingParameter armoryEngraving;

    @JsonProperty(value = "ArmoryCard")
    private ArmoryCardParameter armoryCard;

    @JsonProperty(value = "ArmoryGem")
    private ArmoryGemParameter armoryGem;

    @JsonProperty(value = "ColosseumInfo")
    private ColosseumInfoParameter colosseumInfo;

    @JsonProperty(value = "Collectibles")
    private List<CollectibleParameter> collectibles;

    protected ArmoryParameter() {
    }

}

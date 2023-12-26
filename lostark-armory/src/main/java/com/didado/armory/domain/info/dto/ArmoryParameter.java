package com.didado.armory.domain.info.dto;

import com.didado.armory.domain.info.dto.avatar.ArmoryAvatarParameter;
import com.didado.armory.domain.info.dto.card.ArmoryCardParameter;
import com.didado.armory.domain.info.dto.collectible.CollectibleParameter;
import com.didado.armory.domain.info.dto.colosseum.ColosseumInfoParameter;
import com.didado.armory.domain.info.dto.engraving.ArmoryEngravingParameter;
import com.didado.armory.domain.info.dto.equipment.ArmoryEquipmentParameter;
import com.didado.armory.domain.info.dto.gem.ArmoryGemParameter;
import com.didado.armory.domain.info.dto.profile.ArmoryProfileParameter;
import com.didado.armory.domain.info.dto.skill.ArmorySkillParameter;
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
}

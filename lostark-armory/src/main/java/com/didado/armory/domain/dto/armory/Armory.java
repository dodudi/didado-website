package com.didado.armory.domain.dto.armory;

import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Armory {
    @JsonProperty(value = "ArmoryProfile") private ArmoryProfileParameter armoryProfile;
    @JsonProperty(value = "ArmoryEquipment") private List<ArmoryEquipmentParameter> armoryEquipment;
    @JsonProperty(value = "ArmoryAvatars") private List<ArmoryAvatarParameter> armoryAvatars;
    @JsonProperty(value = "ArmorySkills") private List<ArmorySkillParameter> armorySkills;
    @JsonProperty(value = "ArmoryEngraving") private ArmoryEngraving armoryEngraving;
    @JsonProperty(value = "ArmoryCard") private ArmoryCard armoryCard;
    @JsonProperty(value = "ArmoryGem") private ArmoryGem armoryGem;
    @JsonProperty(value = "ColosseumInfo") private ColosseumInfo colosseumInfo;
    @JsonProperty(value = "Collectibles") private List<Collectible> collectibles;

}

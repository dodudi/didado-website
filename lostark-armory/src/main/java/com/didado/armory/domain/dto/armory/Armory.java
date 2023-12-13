package com.didado.armory.domain.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Armory {
    @JsonProperty(value = "ArmoryProfile") private ArmoryProfile armoryProfile;
    @JsonProperty(value = "ArmoryEquipment") private List<ArmoryEquipment> armoryEquipment;
    @JsonProperty(value = "ArmoryAvatars") private List<ArmoryAvatar> armoryAvatars;
    @JsonProperty(value = "ArmorySkills") private List<ArmorySkill> armorySkills;
    @JsonProperty(value = "ArmoryEngraving") private ArmoryEngraving armoryEngraving;
    @JsonProperty(value = "ArmoryCard") private ArmoryCard armoryCard;
    @JsonProperty(value = "ArmoryGem") private ArmoryGem armoryGem;
    @JsonProperty(value = "ColosseumInfo") private ColosseumInfo colosseumInfo;
    @JsonProperty(value = "Collectibles") private List<Collectible> collectibles;

}

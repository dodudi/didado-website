package com.didado.armory.domain.equipment.dto;

import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ArmoryEquipmentParameter {
    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    public ArmoryEquipment toArmoryEquipment(String characterName) {
        return ArmoryEquipment.builder()
                .characterName(characterName)
                .type(type)
                .name(name)
                .icon(icon)
                .grade(grade)
                .toolTip(toolTip)
                .build();
    }
}

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

    protected ArmoryEquipmentParameter() {
    }

    public ArmoryEquipmentParameter(ArmoryEquipment armoryEquipment) {
        this.type = armoryEquipment.getType();
        this.name = armoryEquipment.getName();
        this.icon = armoryEquipment.getIcon();
        this.grade = armoryEquipment.getGrade();
        this.toolTip = armoryEquipment.getToolTip();
    }

    public ArmoryEquipment toArmoryEquipment() {
        return ArmoryEquipment.builder()
                .type(type)
                .name(name)
                .icon(icon)
                .grade(grade)
                .toolTip(toolTip)
                .build();
    }
}

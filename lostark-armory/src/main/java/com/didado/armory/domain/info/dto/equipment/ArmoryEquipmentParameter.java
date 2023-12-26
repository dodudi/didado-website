package com.didado.armory.domain.info.dto.equipment;

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
}

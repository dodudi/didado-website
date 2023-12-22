package com.didado.armory.domain.equipment.domain;

import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class ArmoryEquipment {
    @Id
    @GeneratedValue
    @Column(name = "armory_equipment_id")
    private Long id;

    private String characterName;
    private String type;
    private String name;
    private String icon;
    private String grade;
    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    protected ArmoryEquipment() {
    }

    @Builder
    public ArmoryEquipment(String characterName, String type, String name, String icon, String grade, String toolTip) {
        this.characterName = characterName;
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.grade = grade;
        this.toolTip = toolTip;
    }

    public ArmoryEquipment updateData(ArmoryEquipmentParameter parameter, String characterName) {
        this.characterName = characterName;
        this.type = parameter.getType();
        this.name = parameter.getName();
        this.icon = parameter.getIcon();
        this.grade = parameter.getGrade();
        this.toolTip = parameter.getToolTip();
        return this;
    }
}

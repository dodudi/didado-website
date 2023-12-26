package com.didado.armory.domain.info.dto.engraving;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ArmoryEngraving {
    @Id
    @GeneratedValue
    @Column(name = "armory_engraving_id")
    private Long id;
}

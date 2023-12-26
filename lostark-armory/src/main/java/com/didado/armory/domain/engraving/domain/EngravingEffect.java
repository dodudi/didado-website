package com.didado.armory.domain.engraving.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class EngravingEffect {
    @Id
    @GeneratedValue
    @Column(name = "engraving_effect_id")
    private Long id;

    private String icon;

    private String name;

    private String description;
}

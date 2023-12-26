package com.didado.armory.domain.card.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Effect {
    @Id
    @GeneratedValue
    @Column(name = "effect_id")
    private Long id;
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;
}

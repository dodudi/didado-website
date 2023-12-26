package com.didado.armory.domain.gem.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter

public class GemEffect {
    @Id
    @GeneratedValue
    @Column(name = "gem_effect_id")
    private Long id;

    private String gemSlot;

    private String name;

    private String description;

    private String icon;
    @Column(name = "tool_tip", length = 10000)
    private String toolTip;
}

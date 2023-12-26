package com.didado.armory.domain.gem.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Gem {
    @Id
    @GeneratedValue
    @Column(name = "gem_id")
    private Long id;

    private String slot;
    private String name;
    private String icon;
    private String level;
    private String grade;
    @Column(name = "tool_tip", length = 10000)
    private String toolTip;
}

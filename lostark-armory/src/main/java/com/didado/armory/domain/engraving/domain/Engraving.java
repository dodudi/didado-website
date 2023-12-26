package com.didado.armory.domain.engraving.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Engraving {
    @Id
    @GeneratedValue
    @Column(name = "engraving_id")
    private Long id;

    private Integer slot;

    private String name;

    private String icon;

    private String toolTip;
}

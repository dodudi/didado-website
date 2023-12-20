package com.didado.armory.domain.profile.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Tooltip {
    @Id
    @GeneratedValue
    @Column(name = "tooltip_id")
    private Long id;

    private String toolTip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stat_id")
    private Stat stat;
}

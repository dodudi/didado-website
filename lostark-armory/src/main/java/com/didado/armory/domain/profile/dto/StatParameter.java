package com.didado.armory.domain.profile.dto;

import com.didado.armory.domain.profile.domain.Stat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class StatParameter {
    @JsonProperty(value = "Type")
    private String type;
    @JsonProperty(value = "Value")
    private String value;
    @JsonProperty(value = "Tooltip")
    private List<String> toolTip;

    protected StatParameter() {
    }

    public StatParameter(Stat stat) {
        this.type = stat.getType();
        this.value = stat.getValue();
        this.toolTip = stat.getToolTip();
    }

    public Stat toStat() {
        return Stat.builder()
                .type(type)
                .value(value)
                .toolTip(toolTip)
                .build();
    }
}

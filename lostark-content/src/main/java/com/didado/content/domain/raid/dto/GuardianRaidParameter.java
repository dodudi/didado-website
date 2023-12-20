package com.didado.content.domain.raid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GuardianRaidParameter {

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "MinCharacterLevel")
    private Integer minCharacterLevel;

    @JsonProperty(value = "MinItemLevel")
    private Integer minItemLevel;

    @JsonProperty(value = "StartTime")
    private String startTime;

    @JsonProperty(value = "EndTime")
    private String endTime;

    @JsonProperty(value = "Image")
    private String image;
}

package com.website.didado.domain.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SkillOption {

    @JsonProperty(value = "Value")
    private Integer value;

    @JsonProperty(value = "Class")
    private String className;

    @JsonProperty(value = "Text")
    private String text;

    @JsonProperty(value = "IsSkillGroup")
    private Boolean isSkillGroup;

    @JsonProperty(value = "Tripods")
    private List<Tripod> tripods;
}

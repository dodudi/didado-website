package com.didado.market.domain.lostark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CategoryItem {

    @JsonProperty(value = "Code")
    private Integer code;

    @JsonProperty(value = "CodeName")
    private String codeName;
}

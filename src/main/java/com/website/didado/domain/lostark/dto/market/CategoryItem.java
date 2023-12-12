package com.website.didado.domain.lostark.dto.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CategoryItem {

    @JsonProperty(value = "Code")
    private Integer code;

    @JsonProperty(value = "CodeName")
    private String codeName;
}

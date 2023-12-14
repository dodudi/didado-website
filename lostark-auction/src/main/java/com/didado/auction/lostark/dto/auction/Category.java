package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Category {

    @JsonProperty(value = "Subs")
    private List<CategoryItem> subs;

    @JsonProperty(value = "Code")
    private Integer code;

    @JsonProperty(value = "CodeName")
    private String codeName;
}

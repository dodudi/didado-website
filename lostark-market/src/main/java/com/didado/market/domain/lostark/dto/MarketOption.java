package com.didado.market.domain.lostark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class MarketOption {

    @JsonProperty(value = "Categories")
    private List<Category> categories;

    @JsonProperty(value = "ItemGrades")
    private List<String> itemGrades;

    @JsonProperty(value = "ItemTiers")
    private List<Integer> itemTiers;

    @JsonProperty(value = "Classes")
    private List<String> classes;
}

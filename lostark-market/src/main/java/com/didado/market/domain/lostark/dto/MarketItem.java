package com.didado.market.domain.lostark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MarketItem {

    @JsonProperty(value = "Id")
    private Integer id;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "BundleCount")
    private Integer bundleCount;

    @JsonProperty(value = "TradeRemainCount")
    private Integer tradeRemainCount;

    @JsonProperty(value = "YDayAvgPrice")
    private Double yDayAvgPrice;

    @JsonProperty(value = "RecentPrice")
    private Integer recentPrice;

    @JsonProperty(value = "CurrentMinPrice")
    private Integer currentMinPrice;
}

package com.didado.market.domain.lostark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MarketItemStats {

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "TradeRemainCount")
    private Integer tradeRemainCount;

    @JsonProperty(value = "BundleCount")
    private Integer bundleCount;

    @JsonProperty(value = "Stats")
    private List<MarketStatsInfo> stats;

    @JsonProperty(value = "ToolTip")
    private String toolTip;
}

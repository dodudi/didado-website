package com.website.didado.domain.lostark.dto.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MarketStatsInfo {

    @JsonProperty(value = "Date")
    private String date;

    @JsonProperty(value = "AvgPrice")
    private Double avgPrice;

    @JsonProperty(value = "TradeCount")
    private Integer tradeCount;
}

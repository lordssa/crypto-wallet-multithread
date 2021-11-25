package com.genesis.global.cripto.application.entities;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    private String id;
    private String rank;
    private String symbol;
    private String name;
    private String supply;
    private String maxSupply;
    private String marketCapUsd;
    private String volumeUsd24Hr;
    private BigDecimal priceUsd;
    private String changePercent24Hr;
    private String vwap24Hr;
}

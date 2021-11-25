package com.genesis.global.cripto.application.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetPosition {
    private Wallet wallet;
    private String symbol;
    private BigDecimal amount;
    private BigDecimal performance;
}

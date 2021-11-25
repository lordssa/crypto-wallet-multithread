package com.genesis.global.cripto.application.entities;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal price;
}

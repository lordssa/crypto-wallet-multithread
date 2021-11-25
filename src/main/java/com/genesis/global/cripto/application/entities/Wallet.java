package com.genesis.global.cripto.application.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private String symbol;
    private String quamtity;
    private String price;
}

package com.genesis.global.cripto.adapters.filesystem.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResource {
    private String symbol;
    private String quantity;
    private String price;
}

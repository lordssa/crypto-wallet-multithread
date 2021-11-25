package com.genesis.global.cripto.adapters.integration.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinCapWrapperResource {
    private AssetResource data;
    private String timestamp;
}

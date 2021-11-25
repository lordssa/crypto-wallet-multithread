package com.genesis.global.cripto.adapters.integration.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinCapListWrapperResource {
    private List<AssetResource> data;
    private String timestamp;
}

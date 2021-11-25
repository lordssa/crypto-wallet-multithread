package com.genesis.global.cripto.adapters.integration;

import com.genesis.global.cripto.adapters.integration.converter.AssetResourceToAssetConverter;
import com.genesis.global.cripto.adapters.integration.resource.CoinCapWrapperResource;
import com.genesis.global.cripto.application.entities.Asset;
import com.genesis.global.cripto.application.ports.out.AssetPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class CoinCapAdapter implements AssetPort {

    private final CoinCapIntegration coinCapIntegration;
    private final AssetResourceToAssetConverter assetResourceToAssetConverter;

    @Override
    public Asset getById(String id) {
        return ofNullable(coinCapIntegration.getAsset(id))
                .map(CoinCapWrapperResource::getData)
                .map(assetResourceToAssetConverter::convert)
                .orElse(Asset.builder().build());
    }
}

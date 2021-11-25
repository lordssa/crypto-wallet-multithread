package com.genesis.global.cripto.adapters.integration;

import com.genesis.global.cripto.adapters.integration.converter.AssetResourceToAssetConverter;
import com.genesis.global.cripto.adapters.integration.resource.CoinCapWrapperResource;
import com.genesis.global.cripto.application.entities.Asset;
import com.genesis.global.cripto.application.ports.out.AssetPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoinCapAdapter implements AssetPort {

    private final CoinCapIntegration coinCapIntegration;
    private final AssetResourceToAssetConverter assetResourceToAssetConverter;
    private Map symbolsCache = new HashMap();

    @Override
    public Asset getBySymbol(String symbol) {
        if (!symbolsCache.containsKey(symbol)) {
             coinCapIntegration.getAssets(symbol).getData()
                     .forEach(assetResource -> {
                                symbolsCache
                                        .putIfAbsent(assetResource.getSymbol(), assetResource.getId());
                            });
        }
        log.info("Submitted request {} at {}", symbol, LocalDateTime.now());
        return ofNullable(symbolsCache.get(symbol))
                .map(id -> coinCapIntegration.getAsset(String.valueOf(id)))
                .map(CoinCapWrapperResource::getData)
                .map(assetResourceToAssetConverter::convert)
                .orElse(Asset.builder().build());
    }
}

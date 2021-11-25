package com.genesis.global.cripto.adapters.integration;

import com.genesis.global.cripto.adapters.integration.resource.AssetResource;
import com.genesis.global.cripto.adapters.integration.resource.CoinCapWrapperResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "apiCoinCap", url = "${api.url.coinCap}")
public interface CoinCapIntegration {

    @GetMapping("/v2/assets")
    List<AssetResource> getAssets();

    @GetMapping("/v2/assets/{id}")
    CoinCapWrapperResource getAsset(@PathVariable("id") String id);
}
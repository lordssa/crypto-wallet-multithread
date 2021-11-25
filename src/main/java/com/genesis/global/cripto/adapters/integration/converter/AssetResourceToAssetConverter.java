package com.genesis.global.cripto.adapters.integration.converter;

import com.genesis.global.cripto.adapters.integration.resource.AssetResource;
import com.genesis.global.cripto.application.entities.Asset;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetResourceToAssetConverter {

    private final ModelMapper modelMapper;

    public Asset convert(AssetResource assetResource) {
        return modelMapper.map(assetResource, Asset.class);
    }
}
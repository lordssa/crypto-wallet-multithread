package com.genesis.global.cripto.adapters.filesystem.converter;

import com.genesis.global.cripto.adapters.filesystem.resource.WalletResource;
import com.genesis.global.cripto.adapters.integration.resource.AssetResource;
import com.genesis.global.cripto.application.entities.Asset;
import com.genesis.global.cripto.application.entities.Wallet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class WalletResourceToWalletConverter {

    private final ModelMapper modelMapper;

    public List<Wallet> convert(List<WalletResource> walletResourceList) {
        return walletResourceList
                .stream()
                .map(this::convert)
                .collect(Collectors.toUnmodifiableList());
    }

    public Wallet convert(WalletResource walletResource) {
        return modelMapper.map(walletResource, Wallet.class);
    }


}
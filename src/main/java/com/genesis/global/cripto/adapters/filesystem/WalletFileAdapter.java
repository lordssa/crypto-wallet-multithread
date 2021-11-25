package com.genesis.global.cripto.adapters.filesystem;

import com.genesis.global.cripto.adapters.filesystem.converter.WalletResourceToWalletConverter;
import com.genesis.global.cripto.application.entities.Wallet;
import com.genesis.global.cripto.application.ports.out.WalletPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class WalletFileAdapter implements WalletPort {

    private CSVParser csvParser;
    private WalletResourceToWalletConverter walletResourceToWalletConverter;

    @Override
    public List<Wallet> getAllSymbols() {
        return ofNullable(csvParser.retrieveWallet())
                .map(walletResourceToWalletConverter::convert)
                .orElse(List.of(Wallet.builder().build()));
    }
}

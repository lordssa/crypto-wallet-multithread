package com.genesis.global.cripto.application.usecases;

import com.genesis.global.cripto.application.ports.in.GetWalletPerformanceUseCase;
import com.genesis.global.cripto.application.ports.out.AssetPort;
import com.genesis.global.cripto.application.ports.out.WalletPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GetWalletPerformance implements GetWalletPerformanceUseCase {

    private AssetPort assetPort;
   // private WalletPort walletPort;

    @Override
    public String execute() {
        //final var walletAssets = walletPort.getAllSymbols();
        final var asset = assetPort.getById("bitcoin");

        log.info("{}", asset);
        return null;
    }
}

package com.genesis.global.cripto.application.usecases;

import com.genesis.global.cripto.application.entities.Asset;
import com.genesis.global.cripto.application.entities.Wallet;
import com.genesis.global.cripto.application.ports.out.AssetPort;
import com.genesis.global.cripto.application.ports.out.WalletPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetWalletPerformanceTest {

    @Mock
    private AssetPort assetPort;

    @Mock
    private WalletPort walletPort;

    @InjectMocks
    GetWalletPerformance getWalletPerformance;

    @Test
    void shouldGetWalletPerformance() {

        final var walletBTC = Wallet
                .builder()
                .price(BigDecimal.ONE)
                .quantity(BigDecimal.TEN)
                .symbol("BTC")
                .build();

        final var walletETH = Wallet
                .builder()
                .price(BigDecimal.TEN)
                .quantity(BigDecimal.ONE)
                .symbol("ETH")
                .build();

        final var walletBNB = Wallet
                .builder()
                .price(BigDecimal.ONE)
                .quantity(BigDecimal.ONE)
                .symbol("BNB")
                .build();

        final var assetBTC = Asset
                .builder()
                .id("bitcoin")
                .symbol("BTC")
                .priceUsd(BigDecimal.TEN)
                .build();

        final var assetETH = Asset
                .builder()
                .id("Etherium")
                .symbol("ETH")
                .priceUsd(BigDecimal.ZERO)
                .build();

        final var assetBNB = Asset
                .builder()
                .id("binance-coin")
                .symbol("BNB")
                .priceUsd(BigDecimal.ONE)
                .build();

        when(walletPort.getAllSymbols()).thenReturn(List.of(walletBTC,walletETH,walletBNB));
        when(assetPort.getBySymbol("BTC")).thenReturn(assetBTC);
        when(assetPort.getBySymbol("ETH")).thenReturn(assetETH);
        when(assetPort.getBySymbol("BNB")).thenReturn(assetBNB);

        final var result = getWalletPerformance.execute();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("total=101"));
        Assertions.assertTrue(result.contains("best_asset=BTC"));
        Assertions.assertTrue(result.contains("best_performance=99.00"));
        Assertions.assertTrue(result.contains("worst_asset=ETH"));
        Assertions.assertTrue(result.contains("worst_performance=-1.00"));
    }
}
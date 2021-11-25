package com.genesis.global.cripto.application.usecases;

import com.genesis.global.cripto.application.entities.Asset;
import com.genesis.global.cripto.application.entities.AssetPosition;
import com.genesis.global.cripto.application.entities.Wallet;
import com.genesis.global.cripto.application.ports.in.GetWalletPerformanceUseCase;
import com.genesis.global.cripto.application.ports.out.AssetPort;
import com.genesis.global.cripto.application.ports.out.WalletPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GetWalletPerformance implements GetWalletPerformanceUseCase {

    private final AssetPort assetPort;
    private final WalletPort walletPort;
    private final static int MAX_THREADS_PARALLEL = 3;

    public GetWalletPerformance(AssetPort assetPort, WalletPort walletPort) {
        this.assetPort = assetPort;
        this.walletPort = walletPort;
    }

    @Override
    public String execute() {
        ForkJoinPool customThreadPool = null;
        try {
            customThreadPool = new ForkJoinPool(MAX_THREADS_PARALLEL);
            log.info("Now is " + LocalDateTime.now());

            var listAssetPosition = customThreadPool
                    .submit(this::getListAssetPosition)
                    .get();

            return getPerformance(listAssetPosition);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        } finally {
            if (customThreadPool != null) {
                customThreadPool.shutdown();
            }
        }
        return "It was not possible to process your request at this moment";
    }

    private List<AssetPosition> getListAssetPosition() {
        return walletPort.getAllSymbols()
                .parallelStream()
                .map(wallet -> {
                    final var asset = assetPort.getBySymbol(wallet.getSymbol());
                    return calculatePosition(wallet, asset);
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private AssetPosition calculatePosition(Wallet wallet, Asset asset) {
        log.info("Current " + asset.getSymbol() + " position is " + wallet.getQuantity().multiply(asset.getPriceUsd()));
        final var actualPosition = wallet.getQuantity().multiply(asset.getPriceUsd());
        final var performance = (actualPosition.subtract(wallet.getPrice()))
                .divide(wallet.getPrice(), 2, RoundingMode.HALF_UP);

        return AssetPosition
                .builder()
                .amount(actualPosition)
                .wallet(wallet)
                .performance(performance)
                .symbol(asset.getSymbol())
                .build();
    }

    private String getPerformance(List<AssetPosition> assetPositionList) {
        final var total = assetPositionList
                .stream()
                .map(AssetPosition::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var symbolsPerformances = assetPositionList
                .stream()
                .collect(Collectors.toMap(AssetPosition::getSymbol, AssetPosition::getPerformance));

        final var bestPerformance = getBestPerformance(symbolsPerformances);
        final var worstPerformance = getWorstPerformance(symbolsPerformances);

        return "total=" + total + ", best_asset=" + bestPerformance.getKey() +
                ", best_performance=" + bestPerformance.getValue() +
                ", worst_asset=" + worstPerformance.getKey() +
                ", worst_performance=" + worstPerformance.getValue();
    }

    private Map.Entry<String, BigDecimal> getBestPerformance(Map<String, BigDecimal> performances) {
        final var maxEntry = performances.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        return maxEntry.orElse(Map.entry("", BigDecimal.ZERO));
    }

    private Map.Entry<String, BigDecimal> getWorstPerformance(Map<String, BigDecimal> performances) {
        final var minEntry = performances.entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue));
        return minEntry.orElse(Map.entry("", BigDecimal.ZERO));
    }
}

package com.genesis.global.cripto.adapters.web;

import com.genesis.global.cripto.application.ports.in.GetWalletPerformanceUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/")
public class CryptoWalletController {

    GetWalletPerformanceUseCase getWalletPerformanceUseCase;

    @GetMapping
    public String getPerformance() {
        return getWalletPerformanceUseCase.execute();
    }
}
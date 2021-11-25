package com.genesis.global.cripto.application.ports.out;

import com.genesis.global.cripto.application.entities.Wallet;

import java.util.List;

public interface WalletPort {

    List<Wallet> getAllSymbols();
}

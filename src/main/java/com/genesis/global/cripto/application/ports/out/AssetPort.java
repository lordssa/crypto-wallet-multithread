package com.genesis.global.cripto.application.ports.out;

import com.genesis.global.cripto.application.entities.Asset;

public interface AssetPort {

    Asset getBySymbol(String id);
}

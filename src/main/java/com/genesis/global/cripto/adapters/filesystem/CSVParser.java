package com.genesis.global.cripto.adapters.filesystem;

import com.genesis.global.cripto.adapters.filesystem.resource.WalletResource;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CSVParser {

    @Value("${path.file.wallet}")
    private String filePath;

    public List<WalletResource> retrieveWallet() {
        try {
            CSVReader reader =
                    new CSVReaderBuilder(new FileReader(filePath))
                            .withSkipLines(1)
                            .build();
            return reader.readAll().stream().map(data ->
                WalletResource
                        .builder()
                        .symbol(data[0])
                        .quantity(data[1])
                        .price(data[2])
                        .build()
            ).collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

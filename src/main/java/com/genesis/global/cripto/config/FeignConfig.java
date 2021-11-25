package com.genesis.global.cripto.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class FeignConfig {

    @Bean
    @Primary
    public FeignErrorDecoderConfig myErrorDecoder(ObjectMapper objectMapper) {
        return new FeignErrorDecoderConfig(objectMapper);
    }

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
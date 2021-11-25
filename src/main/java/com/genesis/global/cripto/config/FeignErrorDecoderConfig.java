package com.genesis.global.cripto.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesis.global.cripto.adapters.web.exception.BadRequestResourceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class FeignErrorDecoderConfig implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            if (response.status() == 400) {
                final var bodyAsString = getBodyAsString(response);
                return new BadRequestResourceException(bodyAsString);
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    private String getBodyAsString(Response response) {
        try {
            return IOUtils.toString(response.body().asInputStream(), "UTF-8");
        } catch (IOException e) {
            log.error("Feign error decoder exception: ", e);
        }
        return null;
    }
}
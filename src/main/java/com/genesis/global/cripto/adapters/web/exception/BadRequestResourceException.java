package com.genesis.global.cripto.adapters.web.exception;

public class BadRequestResourceException extends RuntimeException {
    public BadRequestResourceException(String message) {
        super(message);
    }
}
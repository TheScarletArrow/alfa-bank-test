package com.example.alphafeigncurrency.feign.exception;

public class NoSuchCurrencyException extends Exception {
    public NoSuchCurrencyException() {
    }

    public NoSuchCurrencyException(String message) {
        super(message);
    }

    public NoSuchCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCurrencyException(Throwable cause) {
        super(cause);
    }

    public NoSuchCurrencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.azure.search.exception;

public class AzureSearchException extends Exception{
    public AzureSearchException() {
    }

    public AzureSearchException(String message) {
        super(message);
    }

    public AzureSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public AzureSearchException(Throwable cause) {
        super(cause);
    }

    public AzureSearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

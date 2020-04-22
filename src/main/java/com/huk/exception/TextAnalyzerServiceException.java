package com.huk.exception;

public class TextAnalyzerServiceException extends RuntimeException {

    public TextAnalyzerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextAnalyzerServiceException(String message) {
        super(message);
    }
}

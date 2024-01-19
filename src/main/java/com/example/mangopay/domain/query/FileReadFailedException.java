package com.example.mangopay.domain.query;

public class FileReadFailedException extends RuntimeException {

    public FileReadFailedException(String message) {
        super(message);
    }

    public FileReadFailedException(Exception exception) {
        super(exception);
    }
}

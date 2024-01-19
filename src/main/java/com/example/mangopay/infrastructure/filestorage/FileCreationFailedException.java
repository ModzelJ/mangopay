package com.example.mangopay.infrastructure.filestorage;

public class FileCreationFailedException extends RuntimeException {

    public FileCreationFailedException(String message) {
        super(message);
    }

    public FileCreationFailedException(Exception exception) {
        super(exception);
    }
}

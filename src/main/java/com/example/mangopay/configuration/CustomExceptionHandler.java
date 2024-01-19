package com.example.mangopay.configuration;

import com.example.mangopay.adapter.in.ideas.dto.ErrorDto;
import com.example.mangopay.domain.query.FileReadFailedException;
import com.example.mangopay.infrastructure.filestorage.FileCreationFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(FileCreationFailedException.class)
    public ResponseEntity<ErrorDto> handleFileCreationFailedException(FileCreationFailedException exception) {
        return ResponseEntity.status(500).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(FileReadFailedException.class)
    public ResponseEntity<ErrorDto> handleFileReadFailedException(FileReadFailedException exception) {
        return ResponseEntity.status(500).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        return ResponseEntity.status(500).body(new ErrorDto(exception.getMessage()));
    }
}

package com.example.mangopay.domain.query;

import java.util.UUID;

public class IdeaNotFoundException extends RuntimeException {
    public IdeaNotFoundException(UUID id) {
        super("Idea with id " + id + " not found");
    }
}

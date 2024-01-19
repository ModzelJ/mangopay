package com.example.mangopay.domain;

import com.example.mangopay.domain.Idea;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IdeasRepository {

    void save(Idea idea);

    Optional<Idea> getById(UUID id);

    List<Idea> getAll();
}

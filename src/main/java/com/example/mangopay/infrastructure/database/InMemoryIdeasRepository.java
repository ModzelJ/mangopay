package com.example.mangopay.infrastructure.database;

import com.example.mangopay.domain.Idea;
import com.example.mangopay.domain.IdeasRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/***
 * This class is a repository implementation that stores ideas in memory.
 * For the sake of simplicity, it does not check user identity. It should be done in real application.
 *
 */

@Repository
public class InMemoryIdeasRepository implements IdeasRepository {

    private final Map<UUID, Idea> ideas = new HashMap<>();

    @Override
    public void save(Idea idea) {
        ideas.put(idea.getId(), idea);
    }

    @Override
    public Optional<Idea> getById(UUID id) {
        return Optional.ofNullable(ideas.get(id));
    }

    @Override
    public List<Idea> getAll() {
        return ideas.values().stream().toList();
    }

}

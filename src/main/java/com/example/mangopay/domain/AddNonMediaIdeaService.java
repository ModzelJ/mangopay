package com.example.mangopay.domain;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddNonMediaIdeaService {

    private final IdeasRepository ideasRepository;

    private final IdeaFactory factory;

    public AddNonMediaIdeaService(IdeasRepository ideasRepository, IdeaFactory factory) {
        this.ideasRepository = ideasRepository;
        this.factory = factory;
    }

    public void addTextIdea(UUID id, String text, String collection, String subcollection) {
        Idea idea = factory.createTextIdea(id, text, collection, subcollection);
        ideasRepository.save(idea);
    }

    public void addUrlIdea(UUID id, String url, String collection, String subcollection) {
        Idea idea = factory.createUrlIdea(id, url, collection, subcollection);
        ideasRepository.save(idea);
    }

}

package com.example.mangopay.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdeaFactory {

    public Idea createTextIdea(UUID id, String text, String collection, String subcollection) {
        return new Idea(
                id,
                IdeaType.TEXT,
                text,
                null,
                null,
                collection,
                subcollection
        );
    }

    public Idea createUrlIdea(UUID id, String url, String collection, String subcollection) {
        return new Idea(
                id,
                IdeaType.URL,
                null,
                url,
                null,
                collection,
                subcollection
        );
    }


    public Idea createMediaIdea(UUID id, String mediaUrl, String collection, String subcollection) {
        return new Idea(
                id,
                IdeaType.MEDIA,
                null,
                null,
                mediaUrl,
                collection,
                subcollection
        );
    }
}

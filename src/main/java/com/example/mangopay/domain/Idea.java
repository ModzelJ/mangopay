package com.example.mangopay.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

public class Idea {

    private final UUID id;

    private final IdeaType type;

    private final String text;

    private final String url;

    private final String mediaUrl;

    private final String collection;

    private final String subcollection;

    public Idea(UUID id, IdeaType type, String text, String url, String mediaUrl, String collection, String subcollection) {
        if(!StringUtils.hasText(collection) && StringUtils.hasText(subcollection)) {
            throw new IllegalArgumentException("Cannot assign subcollection if collection name is empty");
        }
        this.id = id;
        this.type = type;
        this.text = text;
        this.url = url;
        this.mediaUrl = mediaUrl;
        this.collection = collection;
        this.subcollection = subcollection;
    }

    public IdeaType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getCollection() {
        return collection;
    }

    public String getSubcollection() {
        return subcollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idea idea = (Idea) o;
        return Objects.equals(id, idea.id) && type == idea.type && Objects.equals(text, idea.text) && Objects.equals(url, idea.url) && Objects.equals(mediaUrl, idea.mediaUrl) && Objects.equals(collection, idea.collection) && Objects.equals(subcollection, idea.subcollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, text, url, mediaUrl, collection, subcollection);
    }
}

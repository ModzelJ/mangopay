package com.example.mangopay.adapter.in.ideas.dto;

import com.example.mangopay.domain.IdeaType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddTextIdeaDto.class, name = "TEXT"),
        @JsonSubTypes.Type(value = AddUrlIdeaDto.class, name = "URL"),
        @JsonSubTypes.Type(value = AddMediaIdeaDto.class, name = "MEDIA")
})
public abstract class AddIdeaDto {
    protected final IdeaType type;

    protected final String collection;

    protected final String subcollection;

    public AddIdeaDto(IdeaType type, String collection, String subcollection) {
        this.type = type;
        this.collection = collection;
        this.subcollection = subcollection;
    }

    public IdeaType getType() {
        return type;
    }

    public String getCollection() {
        return collection;
    }

    public String getSubcollection() {
        return subcollection;
    }

}

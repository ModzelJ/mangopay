package com.example.mangopay.adapter.in.ideas.dto;

import com.example.mangopay.domain.IdeaType;

public class AddTextIdeaDto extends AddIdeaDto{

    private final String text;

    public AddTextIdeaDto(String text, String collection, String subcollection) {
        super(IdeaType.TEXT, collection, subcollection);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

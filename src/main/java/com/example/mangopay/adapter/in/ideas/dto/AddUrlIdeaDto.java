package com.example.mangopay.adapter.in.ideas.dto;

import com.example.mangopay.domain.IdeaType;

public class AddUrlIdeaDto extends AddIdeaDto{

    private final String url;

    public AddUrlIdeaDto(String url, String collection, String subcollection) {
        super(IdeaType.URL, collection, subcollection);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

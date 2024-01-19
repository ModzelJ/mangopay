package com.example.mangopay.adapter.in.ideas.dto;

import com.example.mangopay.domain.IdeaType;
import org.springframework.web.multipart.MultipartFile;

public class AddMediaIdeaDto extends AddIdeaDto{

    private final MultipartFile file;

    public AddMediaIdeaDto(MultipartFile file, String collection, String subcollection) {
        super(IdeaType.MEDIA, collection, subcollection);
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}

package com.example.mangopay.domain;

import com.example.mangopay.infrastructure.filestorage.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class AddMediaIdeaService {

    private final IdeasRepository ideasRepository;

    private final IdeaFactory factory;

    private final FileRepository fileRepository;

    public AddMediaIdeaService(IdeasRepository ideasRepository, IdeaFactory factory, FileRepository fileRepository) {
        this.ideasRepository = ideasRepository;
        this.factory = factory;
        this.fileRepository = fileRepository;
    }

    public void addMediaIdea(UUID id, MultipartFile file, String collection, String subcollection) {
        var filePath = fileRepository.save(file);

        Idea idea = factory.createMediaIdea(id, filePath, collection, subcollection);
        ideasRepository.save(idea);
    }

}

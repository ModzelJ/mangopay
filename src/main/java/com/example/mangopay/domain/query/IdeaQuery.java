package com.example.mangopay.domain.query;

import com.example.mangopay.adapter.in.ideas.dto.GetIdeaDto;
import com.example.mangopay.configuration.FakeSecurityContextHolder;
import com.example.mangopay.domain.Idea;
import com.example.mangopay.domain.IdeaType;
import com.example.mangopay.domain.IdeasRepository;
import com.example.mangopay.infrastructure.filestorage.FileRepository;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class IdeaQuery {

    private final IdeasRepository ideasRepository;

    private final FakeSecurityContextHolder securityContextHolder;

    private final FileRepository fileRepository;

    public IdeaQuery(IdeasRepository ideasRepository, FakeSecurityContextHolder securityContextHolder, FileRepository fileRepository) {
        this.ideasRepository = ideasRepository;
        this.securityContextHolder = securityContextHolder;
        this.fileRepository = fileRepository;
    }

    public GetIdeaDto getIdea(UUID id) {
        return ideasRepository.getById(id).map(this::mapToDto).orElseThrow(() -> new IdeaNotFoundException(id));
    }

    private GetIdeaDto mapToDto(Idea idea) {
        byte[] file = null;
        if(idea.getType() == IdeaType.MEDIA) {
            var fileFromStorage = fileRepository.getByPath(idea.getMediaUrl()).orElseThrow(() -> new RuntimeException("File not found"));
            try {
                InputStream in = new FileInputStream(fileFromStorage);
                file = in.readAllBytes();
            }
            catch (IOException e) {
                throw new FileReadFailedException(e);
            }
        }

        return new GetIdeaDto(idea.getId(), idea.getType(), idea.getCollection(), idea.getSubcollection(), idea.getText(),
                idea.getUrl(), file, securityContextHolder.getUserId());
    }

}

package com.example.mangopay.domain.handler;

import com.example.mangopay.configuration.FakeSecurityContextHolder;
import com.example.mangopay.domain.AddMediaIdeaService;
import com.example.mangopay.domain.AddNonMediaIdeaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddIdeaCommandHandler {

    private static Logger logger = LoggerFactory.getLogger(AddIdeaCommandHandler.class);

    private final AddNonMediaIdeaService nonMediaIdeaService;

    private final AddMediaIdeaService mediaIdeaService;

    private final FakeSecurityContextHolder contextHolder;

    public AddIdeaCommandHandler(AddNonMediaIdeaService nonMediaIdeaService, AddMediaIdeaService mediaIdeaService, FakeSecurityContextHolder contextHolder) {
        this.nonMediaIdeaService = nonMediaIdeaService;
        this.mediaIdeaService = mediaIdeaService;
        this.contextHolder = contextHolder;
    }


    public UUID handle(AddIdeaCommand command) {
        UUID id = UUID.randomUUID();
        UUID userId = contextHolder.getUserId();
        logger.info("Adding idea with id: {} and type {} for user {}", id, command.type(), userId);
        switch (command.type()) {
            case TEXT -> nonMediaIdeaService.addTextIdea(id, command.text(), command.collection(), command.subcollection());
            case URL -> nonMediaIdeaService.addUrlIdea(id, command.url(), command.collection(), command.subcollection());
            case MEDIA -> mediaIdeaService.addMediaIdea(id, command.file(), command.collection(), command.subcollection());
        }
        logger.info("Idea with id: {} and type {} added for user {}", id, command.type(), userId);
        return id;

    }

}

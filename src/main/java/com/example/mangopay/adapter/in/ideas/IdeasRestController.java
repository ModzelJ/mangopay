package com.example.mangopay.adapter.in.ideas;

import com.example.mangopay.adapter.in.ideas.dto.*;
import com.example.mangopay.domain.handler.AddIdeaCommandHandler;
import com.example.mangopay.domain.handler.AddIdeaCommandFactory;
import com.example.mangopay.domain.query.IdeaListQuery;
import com.example.mangopay.domain.query.IdeaQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ideas")
public class IdeasRestController implements MangopayIdeasRepository {

    private final AddIdeaCommandHandler handler;

    private final AddIdeaCommandFactory factory;

    private final IdeaQuery ideaQuery;

    private final IdeaListQuery ideasListQuery;

    private final MediaValidator mediaValidator;

    public IdeasRestController(AddIdeaCommandHandler handler, AddIdeaCommandFactory factory, IdeaQuery ideaQuery, IdeaListQuery ideasListQuery, MediaValidator mediaValidator) {
        this.handler = handler;
        this.factory = factory;
        this.ideaQuery = ideaQuery;
        this.ideasListQuery = ideasListQuery;
        this.mediaValidator = mediaValidator;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> addNonMediaIdea(@RequestBody AddIdeaDto dto) {
        UUID id = handler.handle(factory.createIdeaCommand(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UUID> addMediaIdea(@ModelAttribute AddMediaIdeaDto dto) {
        mediaValidator.validate(dto.getFile());
        UUID id = handler.handle(factory.createIdeaCommand(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GetIdeaDto> getIdeasList(@PathVariable UUID id) {
        var idea = ideaQuery.getIdea(id);
        return ResponseEntity.ok(idea);
    }

    @Override
    @GetMapping
    public ResponseEntity<GetIdeasListDto> getIdea() {
        var idea = ideasListQuery.getIdeas();
        return ResponseEntity.ok(idea);
    }

}

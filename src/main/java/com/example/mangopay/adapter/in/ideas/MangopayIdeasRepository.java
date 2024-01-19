package com.example.mangopay.adapter.in.ideas;

import com.example.mangopay.adapter.in.ideas.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface MangopayIdeasRepository {

    @Operation(summary = "Add idea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Idea created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UUID.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UUID> addNonMediaIdea(@RequestBody AddIdeaDto dto);

    @Operation(summary = "Upload idea file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Idea created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UUID.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UUID> addMediaIdea(@ModelAttribute AddMediaIdeaDto dto);

    @Operation(summary = "Get idea by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Single idea response",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetIdeaDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<GetIdeaDto> getIdeasList(@PathVariable UUID id);

    @Operation(summary = "Get ideas list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ideas list response",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetIdeasListDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping
    ResponseEntity<GetIdeasListDto> getIdea();
}

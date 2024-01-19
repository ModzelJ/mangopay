package com.example.mangopay.adapter.in.ideas.dto;

import com.example.mangopay.domain.IdeaType;

import java.util.UUID;

public record GetIdeaDto(UUID id, IdeaType type, String collection, String subcollection, String text, String url,
                         byte[] file, UUID userId) {
}

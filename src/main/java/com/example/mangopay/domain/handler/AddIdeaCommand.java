package com.example.mangopay.domain.handler;

import com.example.mangopay.domain.IdeaType;
import org.springframework.web.multipart.MultipartFile;

public record AddIdeaCommand(IdeaType type, String collection, String subcollection, String text, String url,
                             MultipartFile file) {

}

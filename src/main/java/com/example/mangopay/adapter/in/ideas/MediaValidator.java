package com.example.mangopay.adapter.in.ideas;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class MediaValidator {

    private static final String JPEG = "image/jpeg";

    public void validate(MultipartFile file) {
        var contentType = file.getContentType();
        if(!Objects.equals(contentType, JPEG))
            throw new IllegalArgumentException("File must be an JPEG image");
    }
}

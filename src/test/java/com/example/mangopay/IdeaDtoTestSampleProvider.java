package com.example.mangopay;

import com.example.mangopay.adapter.in.ideas.dto.AddMediaIdeaDto;
import com.example.mangopay.adapter.in.ideas.dto.AddTextIdeaDto;
import com.example.mangopay.adapter.in.ideas.dto.AddUrlIdeaDto;
import org.springframework.mock.web.MockMultipartFile;

public class IdeaDtoTestSampleProvider {

    public static AddTextIdeaDto getAddTextIdeaDto() {
        return new AddTextIdeaDto("text-text", "test-collection", null);
    }

    public static AddUrlIdeaDto getAddUrlIdeaDto() {
        return new AddUrlIdeaDto("test-url", "test-collection", null);
    }

    public static AddMediaIdeaDto getAddMediaIdeaDto() {
        return new AddMediaIdeaDto(new MockMultipartFile("test-file.jpeg", new byte[]{}), "test-collection", null);
    }
}

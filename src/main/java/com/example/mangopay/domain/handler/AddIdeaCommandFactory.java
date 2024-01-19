package com.example.mangopay.domain.handler;

import com.example.mangopay.adapter.in.ideas.dto.AddIdeaDto;
import com.example.mangopay.adapter.in.ideas.dto.AddMediaIdeaDto;
import com.example.mangopay.adapter.in.ideas.dto.AddTextIdeaDto;
import com.example.mangopay.adapter.in.ideas.dto.AddUrlIdeaDto;
import org.springframework.stereotype.Component;

@Component
public class AddIdeaCommandFactory {

    public AddIdeaCommand createIdeaCommand(AddIdeaDto dto) {
        switch (dto.getType()) {
            case TEXT -> {
                return createIdeaCommand((AddTextIdeaDto) dto);
            }
            case URL -> {
                return createIdeaCommand((AddUrlIdeaDto) dto);
            }
            case MEDIA -> {
                return createIdeaCommand((AddMediaIdeaDto) dto);
            }
        }

        throw new IllegalArgumentException("Unknown idea type provided " + dto.getType());
    }

    public AddIdeaCommand createIdeaCommand(AddTextIdeaDto dto) {
        return new AddIdeaCommand(
                dto.getType(), dto.getCollection(), dto.getSubcollection(),
                dto.getText(), null, null
                );
    }

    public AddIdeaCommand createIdeaCommand(AddUrlIdeaDto dto) {
        return new AddIdeaCommand(
                dto.getType(), dto.getCollection(), dto.getSubcollection(),
                null, dto.getUrl(), null
        );
    }

    public AddIdeaCommand createIdeaCommand(AddMediaIdeaDto dto) {
        return new AddIdeaCommand(
                dto.getType(), dto.getCollection(), dto.getSubcollection(),
                null, null, dto.getFile()
        );
    }
}

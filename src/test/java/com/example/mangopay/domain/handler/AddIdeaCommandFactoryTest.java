package com.example.mangopay.domain.handler;

import com.example.mangopay.IdeaDtoTestSampleProvider;
import com.example.mangopay.domain.IdeaType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddIdeaCommandFactoryTest {

    AddIdeaCommandFactory addIdeaCommandFactory = new AddIdeaCommandFactory();

    @Test
    public void shouldCreateAddIdeaCommandWithTextType() {
        //given
        var dto = IdeaDtoTestSampleProvider.getAddTextIdeaDto();

        //when
        var command = addIdeaCommandFactory.createIdeaCommand(dto);

        //then
        assertEquals(dto.getType(), IdeaType.TEXT);
        assertEquals(dto.getType(), command.type());
        assertEquals(dto.getCollection(), command.collection());
        assertEquals(dto.getSubcollection(), command.subcollection());
        assertEquals(dto.getText(), command.text());
        assertNull(command.url());
        assertNull(command.file());
    }

    @Test
    public void shouldCreateAddIdeaCommandWithUrlType() {
        //given
        var dto = IdeaDtoTestSampleProvider.getAddUrlIdeaDto();

        //when
        var command = addIdeaCommandFactory.createIdeaCommand(dto);

        //then
        assertEquals(dto.getType(), IdeaType.URL);
        assertEquals(dto.getType(), command.type());
        assertEquals(dto.getCollection(), command.collection());
        assertEquals(dto.getSubcollection(), command.subcollection());
        assertEquals(dto.getUrl(), command.url());
        assertNull(command.text());
        assertNull(command.file());
    }

    @Test
    public void shouldCreateAddIdeaCommandWithMediaType() {
        //given
        var dto = IdeaDtoTestSampleProvider.getAddMediaIdeaDto();

        //when
        var command = addIdeaCommandFactory.createIdeaCommand(dto);

        //then
        assertEquals(dto.getType(), IdeaType.MEDIA);
        assertEquals(dto.getType(), command.type());
        assertEquals(dto.getCollection(), command.collection());
        assertEquals(dto.getSubcollection(), command.subcollection());
        assertEquals(dto.getFile(), command.file());
        assertNull(command.text());
        assertNull(command.url());
    }
}
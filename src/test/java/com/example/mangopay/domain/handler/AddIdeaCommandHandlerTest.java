package com.example.mangopay.domain.handler;

import com.example.mangopay.configuration.FakeSecurityContextHolder;
import com.example.mangopay.domain.AddMediaIdeaService;
import com.example.mangopay.domain.AddNonMediaIdeaService;
import com.example.mangopay.domain.IdeaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class AddIdeaCommandHandlerTest {

    @Mock
    AddNonMediaIdeaService nonMediaIdeaService;

    @Mock
    AddMediaIdeaService mediaIdeaService;

    @Mock
    FakeSecurityContextHolder contextHolder;

    @InjectMocks
    AddIdeaCommandHandler addIdeaCommandHandler;

    @Test
    void shouldHandleTextIdea() {
        //given
        var id = java.util.UUID.randomUUID();
        Mockito.when(contextHolder.getUserId()).thenReturn(id);
        var command = new AddIdeaCommand(IdeaType.TEXT, "collection", "subcollection", "text", null, null);

        //when
        addIdeaCommandHandler.handle(command);

        //then
        Mockito.verify(nonMediaIdeaService).addTextIdea(any(), eq(command.text()), eq(command.collection()), eq(command.subcollection()));
    }

    @Test
    void shouldHandleUrlIdea() {
        //given
        var id = java.util.UUID.randomUUID();
        Mockito.when(contextHolder.getUserId()).thenReturn(id);
        var command = new AddIdeaCommand(IdeaType.URL, "collection", "subcollection", null, "test", null);

        //when
        addIdeaCommandHandler.handle(command);

        //then
        Mockito.verify(nonMediaIdeaService).addUrlIdea(any(), eq(command.url()), eq(command.collection()), eq(command.subcollection()));
    }

    @Test
    void shouldHandleMediaIdea() {
        //given
        var id = java.util.UUID.randomUUID();
        Mockito.when(contextHolder.getUserId()).thenReturn(id);
        var command = new AddIdeaCommand(IdeaType.MEDIA, "collection", "subcollection", null, null, new MockMultipartFile("test", new byte[]{}));

        //when
        addIdeaCommandHandler.handle(command);

        //then
        Mockito.verify(mediaIdeaService).addMediaIdea(any(), eq(command.file()), eq(command.collection()), eq(command.subcollection()));
    }
}
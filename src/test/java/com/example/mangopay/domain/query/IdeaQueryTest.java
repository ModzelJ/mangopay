package com.example.mangopay.domain.query;

import com.example.mangopay.configuration.FakeSecurityContextHolder;
import com.example.mangopay.domain.Idea;
import com.example.mangopay.domain.IdeaType;
import com.example.mangopay.domain.IdeasRepository;
import com.example.mangopay.infrastructure.filestorage.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IdeaQueryTest {

    public static final String SRC_TEST_RESOURCES_HACKERMAN_JPEG = "src/test/resources/hackerman.jpeg";
    @Mock
    IdeasRepository ideasRepository;

    @Mock
    FakeSecurityContextHolder securityContextHolder;

    @Mock
    FileRepository fileRepository;

    @InjectMocks
    IdeaQuery ideaQuery;

    @Test
    public void shouldGetSingleIdea() {
        //given
        var idea = new Idea(UUID.randomUUID(), IdeaType.TEXT, "collection", "subcollection", "text", null, null);
        Mockito.when(ideasRepository.getById(Mockito.any())).thenReturn(Optional.of(idea));

        //when
        var result = ideaQuery.getIdea(idea.getId());

        //then
        assertEquals(idea.getId(), result.id());
        assertEquals(idea.getType(), result.type());
        assertEquals(idea.getCollection(), result.collection());
        assertEquals(idea.getSubcollection(), result.subcollection());
        assertEquals(idea.getText(), result.text());

    }

    @Test
    public void shouldGetSingleMediaIdea() {
        //given
        var idea = new Idea(UUID.randomUUID(), IdeaType.MEDIA,  null, null, SRC_TEST_RESOURCES_HACKERMAN_JPEG, "test-collection", null);
        Mockito.when(ideasRepository.getById(Mockito.any())).thenReturn(Optional.of(idea));
        Mockito.when(fileRepository.getByPath(Mockito.any())).thenReturn(Optional.of(new File(SRC_TEST_RESOURCES_HACKERMAN_JPEG)));

        //when
        var result = ideaQuery.getIdea(idea.getId());

        //then
        assertEquals(idea.getId(), result.id());
        assertEquals(idea.getType(), result.type());
        assertEquals(idea.getCollection(), result.collection());
        assertEquals(idea.getSubcollection(), result.subcollection());
        assertNotNull(result.file());
    }

    @Test
    public void shouldFailWhenFileIsNotPresent() {
        //given
        var idea = new Idea(UUID.randomUUID(), IdeaType.MEDIA,  null, null, null, "test-collection", null);
        Mockito.when(ideasRepository.getById(Mockito.any())).thenReturn(Optional.of(idea));
        Mockito.when(fileRepository.getByPath(Mockito.any())).thenReturn(Optional.empty());

        //when
        var exception = assertThrows(RuntimeException.class, () -> ideaQuery.getIdea(idea.getId()));

        //then
        assertEquals("File not found", exception.getMessage());
    }

    @Test
    public void shouldFailWhenThereIsNoIdea() {
        //given
        var id = UUID.randomUUID();
        Mockito.when(ideasRepository.getById(Mockito.any())).thenReturn(Optional.empty());

        //when
        var exception = assertThrows(IdeaNotFoundException.class, () -> ideaQuery.getIdea(id));

        //then
        assertEquals("Idea with id " + id + " not found", exception.getMessage());
    }
}
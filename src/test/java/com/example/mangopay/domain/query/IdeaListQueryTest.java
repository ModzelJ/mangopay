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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IdeaListQueryTest {

    public static final String SRC_TEST_RESOURCES_HACKERMAN_JPEG = "src/test/resources/hackerman.jpeg";
    @Mock
    IdeasRepository ideasRepository;

    @Mock
    FakeSecurityContextHolder securityContextHolder;

    @Mock
    FileRepository fileRepository;

    @InjectMocks
    IdeaListQuery ideaListQuery;

    @Test
    void shouldGetIdeasList() {
        //given
        var idea = new Idea(UUID.randomUUID(), IdeaType.TEXT, "collection", "subcollection", "text", null, null);
        Mockito.when(ideasRepository.getAll()).thenReturn(List.of(idea));

        //when
        var result = ideaListQuery.getIdeas();

        //then
        assertEquals(1, result.ideas().size());
        assertEquals(idea.getId(), result.ideas().get(0).id());
        assertEquals(idea.getType(), result.ideas().get(0).type());
        assertEquals(idea.getCollection(), result.ideas().get(0).collection());
        assertEquals(idea.getSubcollection(), result.ideas().get(0).subcollection());
        assertEquals(idea.getText(), result.ideas().get(0).text());

    }

    @Test
    public void shouldGetMediaIdea() {
        //given
        var idea = new Idea(UUID.randomUUID(), IdeaType.MEDIA,  null, null, SRC_TEST_RESOURCES_HACKERMAN_JPEG, "test-collection", null);
        Mockito.when(ideasRepository.getAll()).thenReturn(List.of(idea));
        Mockito.when(fileRepository.getByPath(Mockito.any())).thenReturn(Optional.of(new File(SRC_TEST_RESOURCES_HACKERMAN_JPEG)));

        //when
        var result = ideaListQuery.getIdeas();

        //then
        assertEquals(1, result.ideas().size());
        assertEquals(idea.getId(), result.ideas().get(0).id());
        assertEquals(idea.getType(), result.ideas().get(0).type());
        assertEquals(idea.getCollection(), result.ideas().get(0).collection());
        assertEquals(idea.getSubcollection(), result.ideas().get(0).subcollection());
        assertNotNull(result.ideas().get(0).file());
    }

    @Test
    public void shouldFailWhenFileIsNotPresent() {
        //given
        var idea = new Idea(UUID.randomUUID(), IdeaType.MEDIA,  null, null, null, "test-collection", null);
        Mockito.when(ideasRepository.getAll()).thenReturn(List.of(idea));
        Mockito.when(fileRepository.getByPath(Mockito.any())).thenReturn(Optional.empty());

        //when
        var exception = assertThrows(RuntimeException.class, () -> ideaListQuery.getIdeas());

        //then
        assertEquals("File not found", exception.getMessage());
    }
}
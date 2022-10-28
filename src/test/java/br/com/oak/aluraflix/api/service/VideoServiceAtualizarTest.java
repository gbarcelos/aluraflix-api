package br.com.oak.aluraflix.api.service;

import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.exception.NotFoundException;
import br.com.oak.aluraflix.api.model.ErrorCode;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.repository.VideoRepository;
import br.com.oak.aluraflix.api.service.mapper.VideoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceAtualizarTest {

    public static final Long VIDEO_ID = 1L;

    @Mock
    private VideoRepository videoRepository;

    @Captor
    private ArgumentCaptor<Video> captor;

    private VideoMapper videoMapper;

    private ModelMapper modelMapper;

    private VideoService videoService;

    @BeforeEach
    public void beforeEach() {
        modelMapper = new ModelMapper();
        modelMapper
                .createTypeMap(Video.class, Video.class)
                .addMappings(
                        mapper -> {
                            mapper.skip(Video::setId);
                        });
        videoMapper = new VideoMapper(modelMapper);
        videoService = new VideoServiceImpl(videoRepository, videoMapper, modelMapper);
    }

    @Test
    public void testAtualizar_cenarioDeSucesso() {
        // Arrange
        VideoInput videoAlterado = VideoInput.builder()
                .titulo("titulo, alterado")
                .descricao("descricao, alterado")
                .url("url, alterado")
                .build();

        Video videoBanco = Video.builder()
                .id(VIDEO_ID)
                .titulo("titulo")
                .descricao("descricao")
                .url("url")
                .build();

        when(videoRepository.findById(VIDEO_ID))
                .thenReturn(Optional.of(videoBanco));

        // Act
        videoService.atualizar(VIDEO_ID, videoAlterado);

        // Assert
        verify(videoRepository, times(1)).save(captor.capture());

        assertNotNull(captor.getValue().getId());
        assertEquals("titulo, alterado", captor.getValue().getTitulo());
        assertEquals("descricao, alterado", captor.getValue().getDescricao());
        assertEquals("url, alterado", captor.getValue().getUrl());
    }

    @Test
    public void testAtualizar_quandoVideoNaoFoiEncontrado_entaoLancaNotFoundException() {
        // Arrange
        when(videoRepository.findById(VIDEO_ID))
                .thenReturn(Optional.empty());

        VideoInput videoAlterado = VideoInput.builder()
                .titulo("titulo, alterado")
                .descricao("descricao, alterado")
                .url("url, alterado")
                .build();

        // Act
        NotFoundException notFoundException =
                assertThrows(
                        NotFoundException.class,
                        () -> videoService.atualizar(VIDEO_ID, videoAlterado));

        // Assert
        assertEquals(ErrorCode.RESOURCE_NOT_FOUND, notFoundException.getErrorCode());
        assertEquals(
                String.format("O registro com o id '%s' não existe", VIDEO_ID),
                notFoundException.getFriendlyMessage());
    }
}

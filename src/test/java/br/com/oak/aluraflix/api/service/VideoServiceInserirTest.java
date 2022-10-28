package br.com.oak.aluraflix.api.service;

import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.repository.VideoRepository;
import br.com.oak.aluraflix.api.service.mapper.VideoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VideoServiceInserirTest {

    @Mock
    private VideoRepository videoRepository;

    private VideoMapper videoMapper;

    private ModelMapper modelMapper;

    private VideoService videoService;

    @BeforeEach
    public void beforeEach() {
        modelMapper = new ModelMapper();
        videoMapper = new VideoMapper(modelMapper);
        videoService = new VideoServiceImpl(videoRepository, videoMapper, modelMapper);
    }

    @Test
    public void testInserir_cenarioDeSucesso() {
        // Arrange
        VideoInput videoInput = VideoInput.builder()
                .titulo("titulo")
                .descricao("descricao")
                .url("url")
                .build();

        // Act
        videoService.inserir(videoInput);

        // Assert
        verify(videoRepository, times(1)).save(any(Video.class));
    }
}

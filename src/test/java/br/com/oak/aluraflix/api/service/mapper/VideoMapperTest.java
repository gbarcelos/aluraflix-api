package br.com.oak.aluraflix.api.service.mapper;

import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VideoMapperTest {

  private VideoMapper videoMapper;
  @Mock private CategoriaRepository categoriaRepository;

  @BeforeEach
  public void beforeEach() {
    videoMapper = new VideoMapper(new ModelMapper(), categoriaRepository);
  }

  @Test
  public void deveMapear_quandoTodosOsCamposObrigatoriosEstaoInformados() {

    VideoInput source =
        VideoInput.builder().titulo("titulo").descricao("descricao").url("url").build();

    Video result = videoMapper.map(source);

    assertEquals(source.getTitulo(), result.getTitulo());
    assertEquals(source.getDescricao(), result.getDescricao());
    assertEquals(source.getUrl(), result.getUrl());
  }
}

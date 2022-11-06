package br.com.oak.aluraflix.api.config;

import br.com.oak.aluraflix.api.entity.Categoria;
import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.CategoriaInput;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper
        .createTypeMap(Video.class, Video.class)
        .addMappings(
            mapper -> {
              mapper.skip(Video::setId);
            });

    modelMapper
        .createTypeMap(Categoria.class, Categoria.class)
        .addMappings(
            mapper -> {
              mapper.skip(Categoria::setId);
            });

    modelMapper
        .createTypeMap(VideoInput.class, Video.class)
        .addMappings(mapper -> mapper.skip(Video::setId));

    modelMapper
        .createTypeMap(CategoriaInput.class, Categoria.class)
        .addMappings(mapper -> mapper.skip(Categoria::setId));

    modelMapper
        .createTypeMap(Video.class, VideoDto.class)
        .addMappings(
            mapper -> mapper.map(src -> src.getCategoria().getTitulo(), VideoDto::setCategoria));

    return modelMapper;
  }
}

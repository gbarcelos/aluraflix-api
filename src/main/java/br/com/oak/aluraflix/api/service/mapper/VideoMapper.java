package br.com.oak.aluraflix.api.service.mapper;

import br.com.oak.aluraflix.api.entity.Categoria;
import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.oak.aluraflix.api.util.Constants.ID_CATEGORIA_LIVRE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VideoMapper {

  private final ModelMapper modelMapper;
  private final CategoriaRepository categoriaRepository;

  public Video map(VideoInput source) {

    Video video = modelMapper.map(source, Video.class);

    Optional<Categoria> categoriaOptional = Optional.empty();
    if (Objects.nonNull(source.getCategoriaId())) {
      categoriaOptional = categoriaRepository.findById(source.getCategoriaId());
    }

    if (!categoriaOptional.isPresent()) {
      categoriaOptional = categoriaRepository.findById(ID_CATEGORIA_LIVRE);
    }

    categoriaOptional.ifPresent(video::setCategoria);

    return video;
  }

  public VideoDto unmap(Video source) {
    return modelMapper.map(source, VideoDto.class);
  }

  public List<VideoDto> unmap(Iterable<Video> videos) {

    List<VideoDto> list = new ArrayList<>();

    videos.forEach(
        lan -> {
          list.add(unmap(lan));
        });

    return list;
  }
}

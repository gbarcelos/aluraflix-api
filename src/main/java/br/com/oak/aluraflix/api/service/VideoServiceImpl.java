package br.com.oak.aluraflix.api.service;

import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.exception.NotFoundException;
import br.com.oak.aluraflix.api.model.ErrorCode;
import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.repository.VideoRepository;
import br.com.oak.aluraflix.api.service.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VideoServiceImpl implements VideoService {

  private final VideoRepository videoRepository;
  private final VideoMapper videoMapper;
  private final ModelMapper modelMapper;

  @Override
  public List<VideoDto> listarVideos(String titulo) {

    if (StringUtils.isNotBlank(titulo)) {
      return videoMapper.unmap(videoRepository.findByTitulo(titulo));
    }
    return videoMapper.unmap(videoRepository.findAll());
  }

  @Override
  public VideoDto detalhar(Long id) {

    Video video = buscarVideoPorId(id);

    return videoMapper.unmap(video);
  }

  @Override
  public VideoDto inserir(VideoInput videoInput) {

    Video video = videoMapper.map(videoInput);

    videoRepository.save(video);

    return videoMapper.unmap(video);
  }

  @Override
  public void atualizar(Long id, VideoInput videoInput) {
    Video videoBanco = buscarVideoPorId(id);
    Video video = videoMapper.map(videoInput);

    modelMapper.map(video, videoBanco);

    videoRepository.save(videoBanco);
  }

  @Override
  public void excluir(Long id) {
    videoRepository.delete(buscarVideoPorId(id));
  }

  private Video buscarVideoPorId(Long id) {
    return videoRepository
        .findById(id)
        .orElseThrow(
            () ->
                new NotFoundException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    String.format("O registro com o id '%s' não existe", id)));
  }
}

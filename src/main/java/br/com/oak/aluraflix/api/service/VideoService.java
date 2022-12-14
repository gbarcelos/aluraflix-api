package br.com.oak.aluraflix.api.service;

import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;

import java.util.List;

public interface VideoService {
  List<VideoDto> listarVideos(String titulo);

  List<VideoDto> listarVideosPorCategoria(Long categoriaId);

  VideoDto detalhar(Long id);

  VideoDto inserir(VideoInput videoInput);

  void atualizar(Long id, VideoInput videoInput);

  void excluir(Long id);
}

package br.com.oak.aluraflix.api.controller;

import br.com.oak.aluraflix.api.controller.openapi.VideosControllerOpenApi;
import br.com.oak.aluraflix.api.model.contract.response.ContractResponse;
import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/v1/videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideosController implements VideosControllerOpenApi {

  private final VideoService videoService;

  @Override
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ContractResponse<List<VideoDto>> listarVideos(
      @RequestParam(required = false) String titulo, HttpServletRequest request) {
    return ContractResponse.<List<VideoDto>>builder()
        .path(request.getServletPath())
        .response(videoService.listarVideos(titulo))
        .build();
  }

  @Override
  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ContractResponse<VideoDto> detalharVideo(
      @PathVariable(value = "id") Long id, HttpServletRequest request) {
    return ContractResponse.<VideoDto>builder()
        .path(request.getServletPath())
        .response(videoService.detalhar(id))
        .build();
  }

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ContractResponse<VideoDto> criarVideo(
      @RequestBody @Valid VideoInput videoInput, HttpServletRequest request) {
    return ContractResponse.<VideoDto>builder()
        .path(request.getServletPath())
        .response(videoService.inserir(videoInput))
        .build();
  }

  @Override
  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarVideo(
      @PathVariable(value = "id") Long id,
      @RequestBody @Valid VideoInput videoInput,
      HttpServletRequest request) {
    videoService.atualizar(id, videoInput);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluirVideo(@PathVariable(value = "id") Long id, HttpServletRequest request) {
    videoService.excluir(id);
  }
}

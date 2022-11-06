package br.com.oak.aluraflix.api.controller.openapi;

import br.com.oak.aluraflix.api.model.contract.response.ContractResponse;
import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "Video")
public interface VideosControllerOpenApi {

  @ApiOperation(
      value = "Lista os videos cadastrados no sistema",
      tags = {"Consultas"})
  @ApiResponses({@ApiResponse(code = 200, message = "Videos listados com sucesso")})
  ContractResponse<List<VideoDto>> listarVideos(
      @ApiParam(name = "titulo", type = "String", value = "Título de um video") String titulo,
      HttpServletRequest request);

  @ApiOperation(
      value = "Detalha um vídeo cadastrado no sistema",
      tags = {"Consultas"})
  @ApiResponses({@ApiResponse(code = 200, message = "Vídeo detalhado com sucesso")})
  ContractResponse<VideoDto> detalharVideo(Long id, HttpServletRequest request);

  @ApiOperation("Cria um vídeo no sistema")
  @ApiResponses({@ApiResponse(code = 201, message = "Vídeo criado com sucesso")})
  ContractResponse<VideoDto> criarVideo(
      @ApiParam(
              name = "videoInput",
              value = "A representação das informações para criação de um vídeo")
          VideoInput videoInput,
      HttpServletRequest request);

  @ApiOperation("Atualiza um video no sistema")
  @ApiResponses({@ApiResponse(code = 204, message = "Vídeo atualizado com sucesso")})
  void atualizarVideo(
      Long id,
      @ApiParam(
              name = "videoInput",
              value = "A representação das informações para atualização de um vídeo")
          VideoInput videoInput,
      HttpServletRequest request);

  @ApiOperation("Exclui um vídeo cadastrado no sistema")
  @ApiResponses({@ApiResponse(code = 204, message = "Vídeo excluído com sucesso")})
  void excluirVideo(Long id, HttpServletRequest request);
}

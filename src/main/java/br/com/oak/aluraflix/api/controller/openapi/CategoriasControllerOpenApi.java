package br.com.oak.aluraflix.api.controller.openapi;

import br.com.oak.aluraflix.api.model.contract.response.ContractResponse;
import br.com.oak.aluraflix.api.model.dto.CategoriaDto;
import br.com.oak.aluraflix.api.model.input.CategoriaInput;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "Categoria")
public interface CategoriasControllerOpenApi {

  @ApiOperation(
      value = "Lista as categorias cadastrados no sistema",
      tags = {"Consultas"})
  @ApiResponses({@ApiResponse(code = 200, message = "Categorias listadas com sucesso")})
  ContractResponse<List<CategoriaDto>> listarCategorias(
      @ApiParam(name = "titulo", type = "String", value = "Titulo de uma categoria") String titulo,
      HttpServletRequest request);

  @ApiOperation(
      value = "Detalha uma categoria cadastrada no sistema",
      tags = {"Consultas"})
  @ApiResponses({@ApiResponse(code = 200, message = "Categori detalhada com sucesso")})
  ContractResponse<CategoriaDto> detalharCategoria(Long id, HttpServletRequest request);

  @ApiOperation("Cria uma categoria no sistema")
  @ApiResponses({@ApiResponse(code = 201, message = "Categoria criada com sucesso")})
  ContractResponse<CategoriaDto> criarCategoria(
      @ApiParam(
              name = "categoriaInput",
              value = "A representação das informações para criação de uma categoria")
          CategoriaInput categoriaInput,
      HttpServletRequest request);

  @ApiOperation("Atualiza uma categoria no sistema")
  @ApiResponses({@ApiResponse(code = 204, message = "Categoria atualizada com sucesso")})
  void atualizarCategoria(
      Long id,
      @ApiParam(
              name = "categoriaInput",
              value = "A representação das informações para atualização de uma categoria")
          CategoriaInput categoriaInput,
      HttpServletRequest request);

  @ApiOperation("Exclui uma categoria cadastrada no sistema")
  @ApiResponses({@ApiResponse(code = 204, message = "Categoria excluída com sucesso")})
  void excluirCategoria(Long id, HttpServletRequest request);
}

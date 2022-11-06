package br.com.oak.aluraflix.api.controller;

import br.com.oak.aluraflix.api.controller.openapi.CategoriasControllerOpenApi;
import br.com.oak.aluraflix.api.model.contract.response.ContractResponse;
import br.com.oak.aluraflix.api.model.dto.CategoriaDto;
import br.com.oak.aluraflix.api.model.input.CategoriaInput;
import br.com.oak.aluraflix.api.service.CategoriaService;
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
@RequestMapping(path = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriasController implements CategoriasControllerOpenApi {

  private final CategoriaService categoriaService;

  @Override
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ContractResponse<List<CategoriaDto>> listarCategorias(
      @RequestParam(required = false) String titulo, HttpServletRequest request) {
    return ContractResponse.<List<CategoriaDto>>builder()
        .path(request.getServletPath())
        .response(categoriaService.listarCategorias(titulo))
        .build();
  }

  @Override
  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ContractResponse<CategoriaDto> detalharCategoria(
      @PathVariable(value = "id") Long id, HttpServletRequest request) {
    return ContractResponse.<CategoriaDto>builder()
        .path(request.getServletPath())
        .response(categoriaService.detalhar(id))
        .build();
  }

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ContractResponse<CategoriaDto> criarCategoria(
      @RequestBody @Valid CategoriaInput categoriaInput, HttpServletRequest request) {
    return ContractResponse.<CategoriaDto>builder()
        .path(request.getServletPath())
        .response(categoriaService.inserir(categoriaInput))
        .build();
  }

  @Override
  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarCategoria(
      @PathVariable(value = "id") Long id,
      @RequestBody @Valid CategoriaInput categoriaInput,
      HttpServletRequest request) {
    categoriaService.atualizar(id, categoriaInput);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluirCategoria(@PathVariable(value = "id") Long id, HttpServletRequest request) {
    categoriaService.excluir(id);
  }
}

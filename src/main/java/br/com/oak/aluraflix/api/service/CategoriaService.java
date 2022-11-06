package br.com.oak.aluraflix.api.service;

import br.com.oak.aluraflix.api.model.dto.CategoriaDto;
import br.com.oak.aluraflix.api.model.input.CategoriaInput;

import java.util.List;

public interface CategoriaService {

  List<CategoriaDto> listarCategorias(String titulo);

  CategoriaDto detalhar(Long id);

  CategoriaDto inserir(CategoriaInput categoriaInput);

  void atualizar(Long id, CategoriaInput categoriaInput);

  void excluir(Long id);
}

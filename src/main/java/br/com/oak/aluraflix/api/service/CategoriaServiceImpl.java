package br.com.oak.aluraflix.api.service;

import br.com.oak.aluraflix.api.entity.Categoria;
import br.com.oak.aluraflix.api.exception.NotFoundException;
import br.com.oak.aluraflix.api.model.ErrorCode;
import br.com.oak.aluraflix.api.model.dto.CategoriaDto;
import br.com.oak.aluraflix.api.model.input.CategoriaInput;
import br.com.oak.aluraflix.api.repository.CategoriaRepository;
import br.com.oak.aluraflix.api.service.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaRepository categoriaRepository;
  private final CategoriaMapper categoriaMapper;
  private final ModelMapper modelMapper;

  @Override
  public List<CategoriaDto> listarCategorias(String titulo) {
    return categoriaMapper.unmap(categoriaRepository.findAll());
  }

  @Override
  public CategoriaDto detalhar(Long id) {

    Categoria categoria = buscarCategoriaPorId(id);

    return categoriaMapper.unmap(categoria);
  }

  @Override
  public CategoriaDto inserir(CategoriaInput categoriaInput) {

    Categoria categoria = categoriaMapper.map(categoriaInput);

    categoriaRepository.save(categoria);

    return categoriaMapper.unmap(categoria);
  }

  @Override
  public void atualizar(Long id, CategoriaInput categoriaInput) {
    Categoria categoriaBanco = buscarCategoriaPorId(id);
    Categoria categoria = categoriaMapper.map(categoriaInput);

    modelMapper.map(categoria, categoriaBanco);

    categoriaRepository.save(categoriaBanco);
  }

  @Override
  public void excluir(Long id) {
    categoriaRepository.delete(buscarCategoriaPorId(id));
  }

  private Categoria buscarCategoriaPorId(Long id) {
    return categoriaRepository
        .findById(id)
        .orElseThrow(
            () ->
                new NotFoundException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    String.format("O registro com o id '%s' não existe", id)));
  }
}

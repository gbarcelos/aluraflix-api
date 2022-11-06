package br.com.oak.aluraflix.api.service.mapper;

import br.com.oak.aluraflix.api.entity.Categoria;
import br.com.oak.aluraflix.api.model.dto.CategoriaDto;
import br.com.oak.aluraflix.api.model.input.CategoriaInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaMapper {

  private final ModelMapper modelMapper;

  public Categoria map(CategoriaInput source) {
    return modelMapper.map(source, Categoria.class);
  }

  public CategoriaDto unmap(Categoria source) {
    return modelMapper.map(source, CategoriaDto.class);
  }

  public List<CategoriaDto> unmap(Iterable<Categoria> categorias) {

    List<CategoriaDto> list = new ArrayList<>();

    categorias.forEach(
        lan -> {
          list.add(unmap(lan));
        });

    return list;
  }
}

package br.com.oak.aluraflix.api.repository;

import br.com.oak.aluraflix.api.entity.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
  List<Categoria> findAllByTituloLike(String titulo);
}

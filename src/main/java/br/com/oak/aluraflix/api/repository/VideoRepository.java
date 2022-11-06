package br.com.oak.aluraflix.api.repository;

import br.com.oak.aluraflix.api.entity.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {

  @Query("from Video v join fetch v.categoria")
  List<Video> findAll();

  @Query("from Video v join fetch v.categoria where v.titulo like %:titulo%")
  List<Video> findByTitulo(String titulo);

  @Query("from Video v join fetch v.categoria where v.categoria.id = :categoriaId")
  List<Video> findByCategoriaId(Long categoriaId);
}

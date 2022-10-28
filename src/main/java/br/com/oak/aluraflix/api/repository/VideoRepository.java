package br.com.oak.aluraflix.api.repository;

import br.com.oak.aluraflix.api.entity.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
}

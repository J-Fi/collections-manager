package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

    @Override
    Optional<Film> findById(Long id);

    @Override
    List<Film> findAll();

    @Query
    List<Film> findAll(@Param("searchText") String searchText);

    @Query
    List<Film> findByFilmsCollectionId(@Param("filmsCollectionId") Long filmsCollectionId, @Param("searchText") String searchText);

    Long countFilmsByFilmsCollection_FilmsCollectionId(@NotNull Long filmsCollection_filmsCollectionId);
}

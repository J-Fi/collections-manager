package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmsCollectionRepository extends CrudRepository<FilmsCollection, Long> {

    @Override
    List<FilmsCollection> findAll();
}

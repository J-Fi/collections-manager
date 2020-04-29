package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import org.springframework.data.repository.CrudRepository;

public interface FilmsCollectionRepository extends CrudRepository<FilmsCollection, Long> {

}

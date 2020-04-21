package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.BooksCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksCollectionRepository extends CrudRepository<BooksCollection, Long> {
}

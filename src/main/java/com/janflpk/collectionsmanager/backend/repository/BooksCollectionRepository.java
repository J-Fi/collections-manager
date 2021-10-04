package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BooksCollectionRepository extends CrudRepository<BooksCollection, Long> {

    @Override
    List<BooksCollection> findAll();

    List<BooksCollection> getBooksCollectionsByUserId(@Param("userId") Long userId);

    Long getNumberOfBooks(@Param("booksCollectionId") Long booksCollectionId);
}

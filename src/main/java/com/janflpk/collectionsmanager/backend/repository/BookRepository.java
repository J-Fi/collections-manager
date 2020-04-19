package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    Optional<Book> findById(Long id);

    @Override
    List<Book> findAll();

    @Query
    List<Book> getBooksListByBooksCollectionId(@Param("BOOKSCOLLECTIONID") Long id);
}

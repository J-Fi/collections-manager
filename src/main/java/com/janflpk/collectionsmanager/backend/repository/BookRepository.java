package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface  BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Query
    List<Book> findAll(@Param("searchText") String searchText);

    @Query
    List<Book> findByBooksCollectionId(@Param("booksCollectionId") Long booksCollectionId);
}

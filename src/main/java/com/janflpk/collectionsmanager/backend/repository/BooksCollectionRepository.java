package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.BooksCollection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BooksCollectionRepository extends CrudRepository<BooksCollection, Long> {

    @Query
    List<BooksCollection> getBooksCollectionsByUserId(@Param ("USERID") Long userId);
}

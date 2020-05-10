package com.janflpk.collectionsmanager.backend.domain.books;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "BooksCollection.getBooksCollectionsByUserId",
        query = "FROM BooksCollection WHERE user_Id = :USERID"
)
@NamedQuery(
        name = "BooksCollection.getNumberOfBooks",
        query = "FROM BooksCollection WHERE books_collection_id = :BOOKSCOLLECTIONID"
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS_COLLECTIONS")
public class BooksCollection {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "collection_id", unique = true)
    private Long booksCollectionId;

    @Column(name = "collection_name")
    private String collectionName;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "booksCollection",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();

    public BooksCollection(String collectionName) {
        this.collectionName = collectionName;
    }

    public BooksCollection(Long booksCollectionId, String collectionName) {
        this.booksCollectionId = booksCollectionId;
        this.collectionName = collectionName;
    }
}

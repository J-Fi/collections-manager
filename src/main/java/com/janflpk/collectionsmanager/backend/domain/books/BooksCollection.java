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
        query = "FROM BooksCollection WHERE user_Id = :userId"
)
@NamedQuery(
        name = "BooksCollection.getNumberOfBooks",
        query = "FROM BooksCollection WHERE books_collection_id = :booksCollectionId"
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

    //@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ManyToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooksCollection)) return false;

        BooksCollection that = (BooksCollection) o;

        if (getBooksCollectionId() != null ? !getBooksCollectionId().equals(that.getBooksCollectionId()) : that.getBooksCollectionId() != null)
            return false;
        if (getCollectionName() != null ? !getCollectionName().equals(that.getCollectionName()) : that.getCollectionName() != null)
            return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        return getBooks() != null ? getBooks().equals(that.getBooks()) : that.getBooks() == null;
    }

    @Override
    public int hashCode() {
        int result = getBooksCollectionId() != null ? getBooksCollectionId().hashCode() : 0;
        result = 31 * result + (getCollectionName() != null ? getCollectionName().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getBooks() != null ? getBooks().hashCode() : 0);
        return result;
    }
}

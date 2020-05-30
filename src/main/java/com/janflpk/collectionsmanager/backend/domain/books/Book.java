package com.janflpk.collectionsmanager.backend.domain.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries ({
    @NamedQuery(
            name = "Book.findAll",
            query = "FROM Book WHERE lower(title) like (concat('%', :searchText, '%')) or " +
                    "lower(authors) like (concat('%', :searchText, '%'))"),
    @NamedQuery(
            name = "Book.findByBooksCollectionId",
            query = "FROM Book WHERE books_collection_id = :booksCollectionId and " +
                    "(lower(title) like (concat('%', :searchText, '%')) or " +
                    "lower(authors) like (concat('%', :searchText, '%')))"
                    )
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", unique = true)
    private Long bookId;

    //@Nullable
    @Column(name = "isbn")
    private String isbn;

    //@Nullable
    @Column(name = "isbn13")
    private String isbn13;

    @Column(name = "title")
    private String title;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "synopsys", length = 65535, columnDefinition = "text")
    private String synopsys;

    @Column(name = "image_url")
    private String image;

    @Column(name = "authors", length = 65535, columnDefinition = "text")
    private String authors;

    @Column(name = "subjects", length = 65535, columnDefinition = "text")
    private String subjects;

    @Column(name = "publish_date")
    private Integer publishDate;

    @ManyToOne
    @JoinColumn(name = "books_collection_id")
    private BooksCollection booksCollection;

    public Book(String isbn, String isbn13, String title, String publisher, String synopsys, String image,
                String authors, String subjects, Integer publishDate) {
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.title = title;
        this.publisher = publisher;
        this.synopsys = synopsys;
        this.image = image;
        this.authors = authors;
        this.subjects = subjects;
        this.publishDate = publishDate;
    }

    public Book(@Nullable String isbn, @Nullable String isbn13, String title, String publisher, String synopsys,
                String image, String authors, String subjects, Integer publishDate, BooksCollection booksCollection) {
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.title = title;
        this.publisher = publisher;
        this.synopsys = synopsys;
        this.image = image;
        this.authors = authors;
        this.subjects = subjects;
        this.publishDate = publishDate;
        this.booksCollection = booksCollection;
    }

    public Book(String isbn, String isbn13) {
        this.isbn = isbn;
        this.isbn13 = isbn13;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", synopsys='" + synopsys + '\'' +
                ", image='" + image + '\'' +
                ", authors='" + authors + '\'' +
                ", subjects='" + subjects + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (getBookId() != null ? !getBookId().equals(book.getBookId()) : book.getBookId() != null) return false;
        if (getIsbn() != null ? !getIsbn().equals(book.getIsbn()) : book.getIsbn() != null) return false;
        if (getIsbn13() != null ? !getIsbn13().equals(book.getIsbn13()) : book.getIsbn13() != null) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getPublisher() != null ? !getPublisher().equals(book.getPublisher()) : book.getPublisher() != null)
            return false;
        if (getSynopsys() != null ? !getSynopsys().equals(book.getSynopsys()) : book.getSynopsys() != null)
            return false;
        if (getImage() != null ? !getImage().equals(book.getImage()) : book.getImage() != null) return false;
        if (getAuthors() != null ? !getAuthors().equals(book.getAuthors()) : book.getAuthors() != null) return false;
        if (getSubjects() != null ? !getSubjects().equals(book.getSubjects()) : book.getSubjects() != null)
            return false;
        if (getPublishDate() != null ? !getPublishDate().equals(book.getPublishDate()) : book.getPublishDate() != null)
            return false;
        return getBooksCollection() != null ? getBooksCollection().equals(book.getBooksCollection()) : book.getBooksCollection() == null;
    }

    @Override
    public int hashCode() {
        int result = getBookId() != null ? getBookId().hashCode() : 0;
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (getIsbn13() != null ? getIsbn13().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getPublisher() != null ? getPublisher().hashCode() : 0);
        result = 31 * result + (getSynopsys() != null ? getSynopsys().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getAuthors() != null ? getAuthors().hashCode() : 0);
        result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
        result = 31 * result + (getPublishDate() != null ? getPublishDate().hashCode() : 0);
        result = 31 * result + (getBooksCollection() != null ? getBooksCollection().hashCode() : 0);
        return result;
    }
}

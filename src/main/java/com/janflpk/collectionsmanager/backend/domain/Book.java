package com.janflpk.collectionsmanager.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQuery(
                name = "Book.findAll",
                query = "FROM Book WHERE lower(title) like (concat('%', :searchText, '%')) or " +
                        "lower(authors) like (concat('%', :searchText, '%'))"
        )

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

    @NotNull
    @Column(name = "isbn")
    private String isbn;

    @NotNull
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

    public Book(@NotNull String isbn, @NotNull String isbn13, String title, String publisher, String synopsys,
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

/*    public Book(String isbn, String isbn13, String title, String publisher, String synopsys, String image,
                String authors, String subjects, Integer publishDate, Long booksCollectionId) {
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.title = title;
        this.publisher = publisher;
        this.synopsys = synopsys;
        this.image = image;
        this.authors = authors;
        this.subjects = subjects;
        this.publishDate = publishDate;
    }*/




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
}

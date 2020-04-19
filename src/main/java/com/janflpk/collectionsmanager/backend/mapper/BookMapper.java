package com.janflpk.collectionsmanager.backend.mapper;

import com.janflpk.collectionsmanager.backend.domain.Author;
import com.janflpk.collectionsmanager.backend.domain.Book;
import com.janflpk.collectionsmanager.backend.domain.BookDto;
import com.janflpk.collectionsmanager.backend.domain.Subject;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BookMapper {

    public String mapAuthorsListToString(List<Author> authors) {
        String s = authors.stream().map(Object::toString).collect(Collectors.joining("; "));
        System.out.println("String: " + s);
        return s;
    }

    public List<Author> mapStringToAuthorsList(String authors) {
        List<Author> list = Stream.of(authors.split("; ")).map(Author::new).collect(Collectors.toList());
        System.out.println(list.size());
        return list;
    }

    public String mapSubjectsListToString(List<Subject> subjects) {
        if (subjects != null) {
            String s = subjects.stream().map(Object::toString).collect(Collectors.joining("; "));
            System.out.println("String: " + s);
            return s;
        }
        return "Unavailable";
    }

    public List<Subject> mapStringToSubjectsList(String subjects) {
        if (subjects != null) {
            List<Subject> list = Stream.of(subjects.split("; ")).map(Subject::new).collect(Collectors.toList());
            System.out.println(list.size());
            return list;
        }
        return new ArrayList<>();
    }

/*    public BookToFrontendFromIsbndbDto mapToBookToFrontendFromIsbndbDto(BookDto bookDto) {
        return new BookToFrontendFromIsbndbDto(
                bookDto.getIsbn(),
                bookDto.getIsbn13(),
                bookDto.getTitle(),
                bookDto.getPublisher(),
                bookDto.getSynopsys(),
                bookDto.getImage(),
                mapAuthorsListToString(bookDto.getAuthors()),
                mapSubjectsListToString(bookDto.getSubjects()),
                bookDto.getPublishDate());
    }*/

    public Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getIsbn(),
                bookDto.getIsbn13(),
                bookDto.getTitle(),
                bookDto.getPublisher(),
                bookDto.getSynopsys(),
                bookDto.getImage(),
                mapAuthorsListToString(bookDto.getAuthors()),
                mapSubjectsListToString(bookDto.getSubjects()),
                bookDto.getPublishDate());
    }

    /*public List<BookToFrontendDto> mapToBookToFrontendDtoList(List<Book> bookList) {
        return bookList.stream().
                map(book -> new BookToFrontendDto(book.getBookId(), book.getIsbn(),
                        Optional.ofNullable(book.getIsbn13()).orElse("Unavailable"), book.getTitle(),
                        book.getPublisher(), Optional.ofNullable(book.getSynopsys()).orElse("Unavailable"),
                        book.getImage(), book.getAuthors(),
                        Optional.ofNullable(book.getSubjects()).orElse("Unavailable"), book.getPublishDate(),
                        book.getBooksCollection().getBooksCollectionId())).
                collect(Collectors.toList());
    }*/

/*    public Book mapBookFromFrontendDtoToBook (BookFromFrontendDto bookFromFrontendDto) {
        return new Book(bookFromFrontendDto.getIsbn(), bookFromFrontendDto.getIsbn13(),
                bookFromFrontendDto.getTitle(), bookFromFrontendDto.getPublisher(),
                bookFromFrontendDto.getSynopsys(), bookFromFrontendDto.getImage(),
                bookFromFrontendDto.getAuthors(), bookFromFrontendDto.getSubjects(),
                bookFromFrontendDto.getPublishDate());
    }

    public BookFromFrontendDto mapBookToBookFromFrontendDto (Book book) {
        return new BookFromFrontendDto(book.getIsbn(), book.getIsbn13(),
                book.getTitle(), book.getPublisher(),
                book.getSynopsys(), book.getImage(),
                book.getAuthors(), book.getSubjects(),
                book.getPublishDate());
    }*/
}

package com.janflpk.collectionsmanager.backend.mapper;

import com.janflpk.collectionsmanager.backend.domain.books.Author;
import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BookDto;
import com.janflpk.collectionsmanager.backend.domain.books.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BookMapper {

    public String mapAuthorsListToString(List<Author> authors) {
        if(authors != null) {
            String s = authors.stream().map(Object::toString).collect(Collectors.joining("; "));
            System.out.println("String: " + s);
            return s;
        }
        return "";
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
        return "";
    }

    public List<Subject> mapStringToSubjectsList(String subjects) {
        if (subjects != null) {
            List<Subject> list = Stream.of(subjects.split("; ")).map(Subject::new).collect(Collectors.toList());
            System.out.println(list.size());
            return list;
        }
        return new ArrayList<>();
    }

    public Book mapToBook(BookDto bookDto) {
        if(bookDto == null) {
            return new Book();
        } else {
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
    }
}

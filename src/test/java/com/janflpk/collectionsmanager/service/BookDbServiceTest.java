package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.backend.service.BooksCollectionDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDbServiceTest {

    @Autowired
    private BookDbService bookDbService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BooksCollectionDbService booksCollectionDbService;

    @Test
    public void shouldFetchAllBooksContainingSpecificStringTest() {
        //given
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001);
        Book book2 = new Book("1111111111", "2222222222222","title2",
                "publisher2", "synopsys2", "image2",
                "authors2", "subjects2", 2002);
        Book bk = new Book("3333333333", "4444444444444","title3",
                "publisher3", "synopsys3", "image3",
                "authors3", "subjects3", 2003);

        List<Book> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);

        when(bookRepository.findAll("book")).thenReturn(list);
        //when
        List<Book> returnedList = bookDbService.findAll("book");

        //then
        Assert.assertEquals(2, returnedList.size());
    }

    @Test
    public void shouldSaveBookTest() {
        //given
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001);

        BooksCollection booksCollection = new BooksCollection(1L, "MyBooks");

        //book1.setBooksCollection(booksCollection);
        //System.out.println(book1.getBooksCollection().getBooksCollectionId());

        when(bookRepository.save(book1)).thenReturn(book1);
        when(booksCollectionDbService.findById(1L)).thenReturn(booksCollection);
        //when
        Book bookReturned = bookDbService.saveBook(book1, booksCollection.getBooksCollectionId());

        //then
        Assert.assertEquals("1234567890", bookReturned.getIsbn());
        Assert.assertEquals(Long.valueOf(1L), Long.valueOf(bookReturned.getBooksCollection().getBooksCollectionId()));
    }
}

/*
package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.ui.view.BookListView;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookListViewTest {

    BookListView bookListView;

    @Test
    public void shouldFetchBookWithIsbn10Test() {
        //Given & When
        Book bookReturned = bookListView.getBookWithIsbn("1234567890");

        //Then
        Assert.assertNotNull(bookReturned);
        Assert.assertEquals("1234567890", bookReturned.getIsbn());
        Assert.assertNull(bookReturned.getIsbn13());
        }

    @Test
    public void shouldFetchBookWithIsbn13Test() {
        //Given & When
        Book bookReturned = bookListView.getBookWithIsbn("1234567891011");

        //Then
        Assert.assertNull(bookReturned.getIsbn());
        Assert.assertEquals("1234567891011", bookReturned.getIsbn13());
    }

    @Test
    public void shouldFetchBookWithIsbnNoneTest() {
        //Given & When
        Book bookReturned = bookListView.getBookWithIsbn("1");

        //Then
        Assert.assertEquals(new Book(), bookReturned);
    }

}
*/

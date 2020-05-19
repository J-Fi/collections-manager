package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.ui.view.FilmListView;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmListViewTest {

    FilmListView filmListView = new FilmListView();

    @Test
    public void shouldFetchFilmWithTitleTest() {
        //Given & When
        Film filmReturned = filmListView.getFilmWithTitle("Title");

        //Then
        Assert.assertNotNull(filmReturned);
        Assert.assertEquals("Title", filmReturned.getFilmTitle());
    }
}

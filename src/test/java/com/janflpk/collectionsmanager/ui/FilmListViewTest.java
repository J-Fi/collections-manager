/*
package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.omdb.facade.OmdbFacade;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.ui.view.FilmListView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmListViewTest {

    private FilmListView filmListView;

    @MockBean
    private FilmDbService filmDbService;

    @MockBean
    private OmdbFacade omdbFacade;

    @Before
    public void init() {

        filmListView = new FilmListView(filmDbService, omdbFacade);
    }

    @Test
    public void shouldFetchFilmWithTitleTest() {
        //Given & When
        Film filmReturned = filmListView.getFilmWithTitle("Title");

        //Then
        Assert.assertNotNull(filmReturned);
        Assert.assertEquals("Title", filmReturned.getFilmTitle());
    }
}
*/

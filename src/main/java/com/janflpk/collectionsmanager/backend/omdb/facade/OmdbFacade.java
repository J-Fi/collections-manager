package com.janflpk.collectionsmanager.backend.omdb.facade;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.mapper.FilmMapper;
import com.janflpk.collectionsmanager.backend.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OmdbFacade {

    @Autowired
    private OmdbService omdbService;

    @Autowired
    private FilmMapper filmMapper;

    public Film getFilm(String filmTitle) {
        return filmMapper.mapToFilm(omdbService.getFilm(filmTitle));
    }
}

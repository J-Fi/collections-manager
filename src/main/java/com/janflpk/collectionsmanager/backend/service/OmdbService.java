package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.FilmDto;
import com.janflpk.collectionsmanager.backend.omdb.client.OmdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmdbService {

    @Autowired
    private OmdbClient omdbClient;

    public FilmDto getFilm(String filmTitle) {
        return omdbClient.getFilm(filmTitle);
    }

}

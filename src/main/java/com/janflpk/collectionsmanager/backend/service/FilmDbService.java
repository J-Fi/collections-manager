package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmDbService {

    @Autowired
    private FilmRepository filmRepo;

    @Autowired
    private FilmsCollectionDbService filmsCollectionDbService;

    public List<Film> findAll(String searchText) {
        return filmRepo.findAll(searchText);
    }

    public Film saveFilm (Film film, Long filmsCollectionId) {
        film.setFilmsCollection(filmsCollectionDbService.findById(filmsCollectionId));
        return filmRepo.save(film);
    }

    public void deleteFilm(final Long id) {
        filmRepo.deleteById(id);
    }

    public Optional<Long> countFilmsByFilmsCollection_FilmsCollectionId(final Long id) {
        return Optional.ofNullable(filmRepo.countFilmsByFilmsCollection_FilmsCollectionId(id)).orElse(Optional.ofNullable(0L));
    }

    public Film findById(final Long id) {
        return filmRepo.findById(id).orElse(new Film());
    }



    public List<Film> fetchFilmsByFilmsCollectionId(Long filmsCollectionId) {
        List<Film> list = Optional.ofNullable(filmRepo.findAll()).orElse(new ArrayList<>());
        List<Film> listFiltered = list.stream()
                .filter(film -> (film.getFilmsCollection().getFilmsCollectionId()).equals(filmsCollectionId))
                .collect(Collectors.toList());
        return listFiltered;
    }

    public List<Film> findByFilmsCollectionId(Long filmsCollectionId, String searchText) {
        return filmRepo.findByFilmsCollectionId(filmsCollectionId, searchText);
    }

    public Film updateFilm(final Long filmsCollectionId, final Long filmId, Film film) {
        List<Film> filmsToUpdate = filmRepo.findAll().stream()
                .filter(b -> b.getFilmsCollection().getFilmsCollectionId().equals(filmsCollectionId))
                .filter(b -> b.getFilmId().equals(filmId))
                .collect(Collectors.toList());

        Film filmToUpdate = filmsToUpdate.get(0);

        filmToUpdate.setFilmTitle(film.getFilmTitle());
        filmToUpdate.setYear(film.getYear());
        filmToUpdate.setRuntime(film.getRuntime());
        filmToUpdate.setDirectorName(film.getDirectorName());
        filmToUpdate.setWriters(film.getWriters());
        filmToUpdate.setActors(film.getActors());
        filmToUpdate.setPlot(film.getPlot());
        filmToUpdate.setLanguage(film.getLanguage());
        filmToUpdate.setCountry(film.getCountry());
        filmToUpdate.setPosterLink(film.getPosterLink());
        filmToUpdate.setProduction(film.getProduction());
        return filmRepo.save(filmToUpdate);
    }

/*    public Optional<Long> countFilmsByFilmsCollection_FilmsCollectionId(final Long id) {
        return filmRepo.countFilmsByFilmsCollection_FilmsCollectionId(id);
    }*/
}

package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.repository.FilmsCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmsCollectionDbService {
    @Autowired
    private FilmsCollectionRepository filmsCollectionRepo;

    public FilmsCollection saveFilmsCollection (FilmsCollection filmsCollection) {
        return filmsCollectionRepo.save(filmsCollection);
    }

    public void deleteFilmsCollection(final Long id) {
        filmsCollectionRepo.deleteById(id);
    }

    public FilmsCollection findById(final Long id) {
        return filmsCollectionRepo.findById(id).orElse(new FilmsCollection());
    }

    public List<FilmsCollection> findAll() {
        return filmsCollectionRepo.findAll();
    }
}

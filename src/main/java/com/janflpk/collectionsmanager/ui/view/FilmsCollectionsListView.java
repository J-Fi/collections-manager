package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.backend.service.FilmsCollectionDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

@Route(value = "films-collections", layout = MainLayout.class)
public class FilmsCollectionsListView extends VerticalLayout {

    private Button addNewCollection = new Button("Dodaj nową kolekcję");
    private Button updateCollection = new Button("Edytuj");
    private Button deleteCollection = new Button("Usuń");


    private FilmsCollectionDbService filmsCollectionDbService;

    private FilmDbService filmDbService;

    private Grid<FilmsCollection> filmsCollectionGrid;

    public FilmsCollectionsListView(FilmsCollectionDbService filmsCollectionDbService, FilmDbService filmDbService) {
        this.filmsCollectionDbService = filmsCollectionDbService;
        this.filmDbService = filmDbService;

        H2 filmsCollectionsGridHeader = new H2("Twoje kolekcje filmów");

        configureFilmsCollectionGrid();
        updateFilmsCollectionList();
        add(filmsCollectionsGridHeader, addNewCollection, filmsCollectionGrid);
        setSizeFull();
    }

    private void configureFilmsCollectionGrid() {
        filmsCollectionGrid = new Grid<>(FilmsCollection.class, false);
        filmsCollectionGrid.setSizeFull();
        filmsCollectionGrid.addColumn(FilmsCollection::getCollectionName)
                .setHeader("Nazwa kolekcji")
                .setSortable(true);
        filmsCollectionGrid.addColumn(e -> filmDbService.countFilmsByFilmsCollection_FilmsCollectionId(e.getFilmsCollectionId()).orElse(0L))
                .setHeader("Liczba filmów")
                .setSortable(true);

        filmsCollectionGrid.asSingleSelect().addValueChangeListener(e -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("filmsCollectionId", e.getValue().getFilmsCollectionId().toString());
            this.getUI().ifPresent(ui -> ui.navigate("films", QueryParameters.simple(parameters)));
        });
    }

    private void updateFilmsCollectionList() {
        filmsCollectionGrid.setItems(filmsCollectionDbService.findAll());
    }
}

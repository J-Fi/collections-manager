package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.service.FilmsCollectionDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "films-collections", layout = MainLayout.class)
public class FilmsCollectionsListView extends VerticalLayout {

    private FilmsCollectionDbService filmsCollectionDbService;
    private Grid<FilmsCollection> filmsCollectionGrid;

    public FilmsCollectionsListView(FilmsCollectionDbService filmsCollectionDbService) {
        this.filmsCollectionDbService = filmsCollectionDbService;

        H2 filmsCollectionsGrid = new H2("Twoje kolekcje filmów");

        configureFilmsCollectionGrid();
        updateFilmsCollectionList();
        add(filmsCollectionsGrid, filmsCollectionGrid);
        setSizeFull();
    }

    private void configureFilmsCollectionGrid() {
        filmsCollectionGrid = new Grid<>(FilmsCollection.class, false);
        filmsCollectionGrid.setSizeFull();
        filmsCollectionGrid.addColumn(FilmsCollection::getCollectionName)
                .setHeader("Nazwa kolekcji")
                .setSortable(true);
        filmsCollectionGrid.addColumn(e -> e.getFilms().size())
                .setHeader("Liczba filmów")
                .setSortable(true);
    }

    private void updateFilmsCollectionList() {
        filmsCollectionGrid.setItems(filmsCollectionDbService.findAll());
    }
}

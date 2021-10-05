package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.service.CurrentUserRetriever;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.backend.service.FilmsCollectionDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Route(value = "films-collections", layout = MainLayout.class)
public class FilmsCollectionsListView extends VerticalLayout implements AfterNavigationObserver {

    private Button addNewCollection = new Button("Dodaj nową kolekcję");
    private Button updateCollection = new Button("Edytuj");
    private Button deleteCollection = new Button("Usuń");

    @Autowired
    private CurrentUserRetriever currentUserRetriever;

    @Autowired
    private FilmsCollectionDbService filmsCollectionDbService;

    @Autowired
    private FilmDbService filmDbService;

    private Long userId;

    private Grid<FilmsCollection> filmsCollectionGrid;

    public FilmsCollectionsListView() {

        H2 filmsCollectionsGridHeader = new H2("Twoje kolekcje filmów");

        configureFilmsCollectionGrid();
        //updateFilmsCollectionList();
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
        filmsCollectionGrid.addColumn(new NativeButtonRenderer<FilmsCollection>("Usuń kolekcję",
                clickedItem -> {
                    filmsCollectionDbService.deleteFilmsCollection(clickedItem.getFilmsCollectionId());
                    updateFilmsCollectionsList();
                }));

        filmsCollectionGrid.asSingleSelect().addValueChangeListener(e -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("filmsCollectionId", e.getValue().getFilmsCollectionId().toString());
            this.getUI().ifPresent(ui -> ui.navigate("films", QueryParameters.simple(parameters)));
        });
    }

    //tutaj trzeba uzupełnić kod

    private void updateFilmsCollectionsList() {
        filmsCollectionGrid.setItems(filmsCollectionDbService.findFilmsCollectionsByUserId(userId));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        userId = currentUserRetriever.retrieveUserId();
        updateFilmsCollectionsList();
    }
}

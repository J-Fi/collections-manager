package com.janflpk.collectionsmanager.ui.view.film;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.service.CurrentUserRetriever;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.backend.service.FilmsCollectionDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
        addNewCollection.addClickListener(e -> {
            createNewCollection();
        });
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
        filmsCollectionGrid.addColumn(new NativeButtonRenderer<>("Usuń kolekcję",
                clickedItem -> confirmDeletingCollection(clickedItem.getFilmsCollectionId(), clickedItem.getCollectionName())));
        filmsCollectionGrid.addColumn(new NativeButtonRenderer<>("Zmień nazwę",
                this::changeCollectionName));

        filmsCollectionGrid.asSingleSelect().addValueChangeListener(e -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("filmsCollectionId", e.getValue().getFilmsCollectionId().toString());
            this.getUI().ifPresent(ui -> ui.navigate("films", QueryParameters.simple(parameters)));
        });
    }

    private void confirmDeletingCollection(Long filmsCollectionId, String collectionName) {
        Icon logo = new Icon(VaadinIcon.QUESTION_CIRCLE);

        Dialog dialog = new Dialog();

        Button yesButton = new Button("Tak");
        Button cancelButton = new Button("Anuluj");

        Label label = new Label("Czy na pewno chcesz skasować kolekcję " + collectionName.toUpperCase() + "?");

        HorizontalLayout labelLayout = new HorizontalLayout(logo, label);
        HorizontalLayout buttonsLayout = new HorizontalLayout(yesButton, cancelButton);
        VerticalLayout dialogLayout = new VerticalLayout(labelLayout, buttonsLayout);
        yesButton.addClickListener(e -> {
            filmsCollectionDbService.deleteFilmsCollection(filmsCollectionId);
            dialog.close();
            updateFilmsCollectionsList();
        });
        cancelButton.addClickListener(e -> dialog.close());

        dialog.add(dialogLayout);
        dialog.open();
    }

    public void createNewCollection() {
        Dialog dialog = new Dialog();
        TextField collectionNameInput = new TextField();
        collectionNameInput.setPlaceholder("Podaj nazwę kolekcji...");

        Button saveButton = new Button("Zapisz");
        Button cancelButton = new Button("Anuluj");
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(collectionNameInput, buttonLayout);

        saveButton.addClickListener(e -> {
            filmsCollectionDbService.saveFilmsCollection(new FilmsCollection(collectionNameInput.getValue()), userId);
            dialog.close();
            updateFilmsCollectionsList();
        });

        cancelButton.addClickListener(e -> dialog.close());

        dialog.add(dialogLayout);
        dialog.open();
    }

    private void changeCollectionName(FilmsCollection filmsCollection) {
        Dialog dialog = new Dialog();
        TextField collectionRenameInput = new TextField();
        collectionRenameInput.setPlaceholder("Podaj nową nazwę kolekcji...");

        Button saveButton = new Button("Zapisz");
        Button cancelButton = new Button("Anuluj");
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(collectionRenameInput, buttonLayout);

        saveButton.addClickListener(e -> {
            filmsCollection.setCollectionName(collectionRenameInput.getValue());
            filmsCollectionDbService.updateFilmsCollection(filmsCollection);
            dialog.close();
            updateFilmsCollectionsList();
        });

        cancelButton.addClickListener(e -> dialog.close());

        dialog.add(dialogLayout);
        dialog.open();
    }

    private void updateFilmsCollectionsList() {
        filmsCollectionGrid.setItems(filmsCollectionDbService.findFilmsCollectionsByUserId(userId));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        userId = currentUserRetriever.retrieveUserId();
        updateFilmsCollectionsList();
    }
}

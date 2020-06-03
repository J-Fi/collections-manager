package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.backend.service.BooksCollectionDbService;
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
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

@Route(value = "books-collections", layout = MainLayout.class)
public class BooksCollectionsListView extends VerticalLayout {

    private Button addNewCollection = new Button("Dodaj nową kolekcję");
    private Button updateCollection = new Button("Edytuj");
    private Button deleteCollection = new Button("Usuń");

    private BooksCollectionDbService booksCollectionDbService;

    private BookDbService bookDbService;

    Grid<BooksCollection> booksCollectionGrid;

    public BooksCollectionsListView(BooksCollectionDbService booksCollectionDbService, BookDbService bookDbService) {
        this.booksCollectionDbService = booksCollectionDbService;
        this.bookDbService = bookDbService;

        H2 collectionsGridHeader = new H2("Twoje kolekcje książek");

        configureBooksCollectionGrid();
        updateBooksCollectionsList();
        addNewCollection.addClickListener(e -> {
            createNewCollection();
        });
        add(collectionsGridHeader, addNewCollection, booksCollectionGrid);
        setSizeFull();
    }

    private void configureBooksCollectionGrid() {
        booksCollectionGrid = new Grid<>(BooksCollection.class, false);
        booksCollectionGrid.setSizeFull();
        booksCollectionGrid.addColumn(BooksCollection::getCollectionName)
                .setHeader("Nazwa kolekcji")
                .setSortable(true);
        booksCollectionGrid.addColumn(e -> bookDbService.countBooksByBooksCollection_BooksCollectionId(e.getBooksCollectionId()).orElse(0L))
                .setHeader("Liczba woluminów")
                .setSortable(true);
        booksCollectionGrid.addColumn(new NativeButtonRenderer<>("Usuń kolekcję",
                clickedItem -> confirmDeletingCollection(clickedItem.getBooksCollectionId(), clickedItem.getCollectionName())));

        booksCollectionGrid.asSingleSelect().addValueChangeListener(e -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("booksCollectionId", e.getValue().getBooksCollectionId().toString());
            this.getUI().ifPresent(ui -> ui.navigate("books", QueryParameters.simple(parameters)));
        });
    }

    private void confirmDeletingCollection(Long booksCollectionId, String collectionName) {
        Icon logo = new Icon(VaadinIcon.QUESTION_CIRCLE);

        Dialog dialog = new Dialog();

        Button yesButton = new Button("Tak");
        Button cancelButton = new Button("Anuluj");

        Label label = new Label("Czy na pewno chcesz skasować kolekcję " + collectionName.toUpperCase() + "?");

        HorizontalLayout labelLayout = new HorizontalLayout(logo, label);
        HorizontalLayout buttonsLayout = new HorizontalLayout(yesButton, cancelButton);
        VerticalLayout dialogLayout = new VerticalLayout(labelLayout, buttonsLayout);
        yesButton.addClickListener(e -> {
            booksCollectionDbService.deleteBooksCollection(booksCollectionId);
            dialog.close();
            updateBooksCollectionsList();
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
            booksCollectionDbService.saveBooksCollection(new BooksCollection(collectionNameInput.getValue()));
            dialog.close();
            updateBooksCollectionsList();
        });

        cancelButton.addClickListener(e -> dialog.close());

        dialog.add(dialogLayout);
        dialog.open();
    }

    private void updateBooksCollectionsList() {
        booksCollectionGrid.setItems(booksCollectionDbService.findAll());
    }
}

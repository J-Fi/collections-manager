package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.backend.service.BooksCollectionDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
                clickedItem -> {
                    booksCollectionDbService.deleteBooksCollection(clickedItem.getBooksCollectionId());
                    updateBooksCollectionsList();
                }));

        booksCollectionGrid.asSingleSelect().addValueChangeListener(e -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("booksCollectionId", e.getValue().getBooksCollectionId().toString());
            this.getUI().ifPresent(ui -> ui.navigate("books", QueryParameters.simple(parameters)));
        });
    }

    private void updateBooksCollectionsList() {
        booksCollectionGrid.setItems(booksCollectionDbService.findAll());
    }
}

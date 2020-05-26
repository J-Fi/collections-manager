package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.service.BooksCollectionDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "books-collections", layout = MainLayout.class)
public class BooksCollectionsListView extends VerticalLayout {

    private BooksCollectionDbService booksCollectionDbService;

    Grid<BooksCollection> booksCollectionGrid;

    public BooksCollectionsListView(BooksCollectionDbService booksCollectionDbService) {
        this.booksCollectionDbService = booksCollectionDbService;

        configureBooksCollectionGrid();
        updateBooksCollectionsList();
        add(booksCollectionGrid);
        setSizeFull();
    }

    private void configureBooksCollectionGrid() {
        booksCollectionGrid = new Grid<>(BooksCollection.class, false);
        booksCollectionGrid.setSizeFull();
        booksCollectionGrid.addColumn(BooksCollection::getCollectionName).setHeader("Nazwa kolekcji");
        booksCollectionGrid.addColumn(e -> e.getBooks().size()).setHeader("Liczba woluminów");
    }

    private void updateBooksCollectionsList() {
        booksCollectionGrid.setItems(booksCollectionDbService.findAll());
    }
}

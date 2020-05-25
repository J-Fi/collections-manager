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

    Grid<BooksCollection> booksCollectionGrid = new Grid<>(BooksCollection.class);

    public BooksCollectionsListView(BooksCollectionDbService booksCollectionDbService) {
        this.booksCollectionDbService = booksCollectionDbService;

        configureBooksCollectionGrid();
        updateBooksCollectionsList();
        add(booksCollectionGrid);
        setSizeFull();
    }

    private void configureBooksCollectionGrid() {
        booksCollectionGrid.setSizeFull();
        booksCollectionGrid.setColumns("collectionName");
        //booksCollectionGrid.addColumn(e -> e.getBooks()).setHeader("Number of books");
    }

    private void updateBooksCollectionsList() {
        booksCollectionGrid.setItems(booksCollectionDbService.findAll());
    }
}

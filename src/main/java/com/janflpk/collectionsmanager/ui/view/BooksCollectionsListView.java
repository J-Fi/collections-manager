package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "books-collections", layout = MainLayout.class)
public class BooksCollectionsListView extends VerticalLayout {

    Grid<BooksCollection> booksCollectionGrid = new Grid<>(BooksCollection.class);

    public BooksCollectionsListView() {



        add(booksCollectionGrid);
    }

    private void configureBooksCollectionGrid() {
        booksCollectionGrid.setSizeFull();
        booksCollectionGrid.setColumns("collectionName")
        booksCollectionGrid.addColumn(), "Number of books");
    }
}

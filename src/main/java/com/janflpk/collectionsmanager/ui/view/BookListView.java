package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.Book;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class)
public class BookListView extends VerticalLayout {

    private TextField filterText = new TextField();
    private Grid<Book> bookGrid = new Grid<>(Book.class);
    private BookDbService bookDbService;


    public BookListView(BookDbService bookDbService) {
        this.bookDbService = bookDbService;

        setSizeFull();

        bookGrid.setSizeFull();

        add(filterText, bookGrid);

        configureGrid();
        configureFilterText();

        updateBookList();
    }

    private void configureGrid() {
        bookGrid.addClassName("book-grid-view");
        bookGrid.setSizeFull();
        bookGrid.setColumns("title", "authors", "publishDate");

        bookGrid.getColumnByKey("authors")
                .setFlexGrow(20)
                .setResizable(true);
        bookGrid.getColumnByKey("title")
                .setFlexGrow(30)
                .setResizable(true);
        bookGrid.getColumnByKey("publishDate").setWidth("150px");
    }

    private void configureFilterText() {
        filterText.setPlaceholder("Wpisz tutaj...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateBookList());
    }

    private void updateBookList() {
        bookGrid.setItems(bookDbService.findAll(filterText.getValue()));
    }
}

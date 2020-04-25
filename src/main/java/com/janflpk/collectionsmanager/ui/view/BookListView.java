package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.Book;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class)
public class BookListView extends VerticalLayout {

    private BookForm bookForm;

    private TextField filterText = new TextField();
    private Grid<Book> bookGrid = new Grid<>(Book.class);
    private BookDbService bookDbService;


    public BookListView(BookDbService bookDbService) {
        this.bookDbService = bookDbService;

        addClassName("book-list-view");

        setSizeFull();

        bookGrid.setSizeFull();
        bookGrid.addClassName("content-grid");

        bookForm = new BookForm();
        bookForm.addListener(BookForm.SaveBookEvent.class, this::saveBook);
        bookForm.addListener(BookForm.DeleteBookEvent.class, this::deleteBook);
        bookForm.addListener(BookForm.CloseEvent.class, e -> closeBookForm());

        HorizontalLayout content = new HorizontalLayout(bookGrid, bookForm);
        //Div content = new Div(bookGrid, bookForm);
        content.setSizeFull();

        add(getToolBar(), content);

        configureGrid();
        //configureFilterText();

        updateBookList();

        closeBookForm();
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

        bookGrid.asSingleSelect().addValueChangeListener(e -> editBook(e.getValue()));
    }

    private void configureFilterText() {
        filterText.setPlaceholder("Wpisz tutaj...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateBookList());
    }

    private HorizontalLayout getToolBar() {
        configureFilterText();
        Button addBookButton = new Button("Dodaj nowy rekord");
        addBookButton.addClickListener(click -> addNewBook());
        HorizontalLayout toolBar = new HorizontalLayout(filterText, addBookButton);
        return toolBar;
    }

    private void addNewBook() {
        bookGrid.asSingleSelect().clear();
        editBook(new Book());
    }

    private void editBook(Book book) {
        if(book == null) {
            closeBookForm();
        } else {
            System.out.println("editBook() " + (book == null));
            bookForm.setBook(book);
            bookForm.setVisible(true);
            bookForm.addClassName("editing");
        }
    }

    private void updateBookList() {
        bookGrid.setItems(bookDbService.findAll(filterText.getValue()));
    }

    private void saveBook(BookForm.SaveBookEvent event) {
        bookDbService.saveBook(event.getBook(), 3L);
        updateBookList();
        closeBookForm();
    }

    private void deleteBook(BookForm.DeleteBookEvent event) {
        bookDbService.deleteBook(event.getBook().getBookId());
        updateBookList();
        closeBookForm();
    }

    private void closeBookForm() {
        bookForm.setBook(null);
        bookForm.setVisible(false);
        bookForm.addClassName("editing");
    }
}

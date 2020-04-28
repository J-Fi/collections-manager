package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.Book;
import com.janflpk.collectionsmanager.backend.isbndb.facade.IsbndbFacade;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Route(value = "", layout = MainLayout.class)
public class BookListView extends VerticalLayout {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookListView.class);

    private BookForm bookForm;
    private BookFullView bookView;

    private Dialog bookViewPopupWindow = new Dialog();

    TextField isbnInput = new TextField("");

    private TextField filterText = new TextField();
    private Grid<Book> bookGrid = new Grid<>(Book.class);
    private BookDbService bookDbService;

    private Dialog isbnInputPopupWindow = new Dialog();
    private IsbndbFacade isbndbFacade;

    public BookListView(BookDbService bookDbService, IsbndbFacade isbndbFacade) {
        this.bookDbService = bookDbService;
        this.isbndbFacade = isbndbFacade;

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

        add(getToolBar(), bookView, content);

        configureGrid();

        configureIsbnInputPopupWindow();
        //configureFilterText();

        updateBookList();

        closeBookForm();
        closeBookView();
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

        bookGrid.asSingleSelect().addValueChangeListener(e -> editBook(e.getValue())); //editBook(e.getValue())
    }

    private void configureFilterText() {
        filterText.setPlaceholder("Wpisz tutaj...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateBookList());
    }

    private void configureIsbnInputPopupWindow() {
        Button search = new Button("Szukaj");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        search.addClickShortcut(Key.ENTER);
        search.addClickListener(e -> searchBookByIsbn());

        Button cancel = new Button("Anuluj");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);
        cancel.addClickListener(e -> cancelIsbnSearch());

        isbnInput.setAutofocus(true);
        isbnInput.setPlaceholder("Wpisz nr isbn...");
        isbnInput.setClearButtonVisible(true);

        HorizontalLayout buttons = new HorizontalLayout(search, cancel);
        VerticalLayout layout = new VerticalLayout(isbnInput, buttons);
        isbnInputPopupWindow.add(layout);
    }

/*    private void getBookViewPopupWindow(Book book) {
        bookView = new BookFullView(book);
        bookView.addListener(BookFullView.SaveBookEvent.class, e -> vali);
        bookViewPopupWindow.add(bookView);
    }*/

    private HorizontalLayout getToolBar() {
        configureFilterText();
        Button addBookButton = new Button("Dodaj nowy rekord");
        addBookButton.addClickListener(click -> isbnInputPopupWindow.open());
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

    private void closeBookView() {
        bookView.setVisible(false);
    }

    public void searchBookByIsbn() {
        Book book = isbndbFacade.getBook(getIsbnInput().getValue());
        isbnInput.clear();
        isbnInputPopupWindow.close();
        if(book == null) {
            addNewBook();
        } else {
            editBook(book);
        }
    }

    private void showBookView(Book book) {
        bookGrid.setVisible(false);
        bookView.setVisible(true);
        bookView.setBook(book);
        bookView.setSizeFull();
    }

    public void cancelIsbnSearch() {
        isbnInputPopupWindow.close();
    }
}

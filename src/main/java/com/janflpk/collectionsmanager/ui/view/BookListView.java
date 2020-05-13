package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.isbndb.facade.IsbndbFacade;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

@Getter
@Route(value = "books", layout = MainLayout.class)
public class BookListView extends VerticalLayout {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookListView.class);

    private BookForm bookForm;

    private Dialog bookViewPopupWindow= new Dialog();
    private Dialog isbnInputPopupWindow = new Dialog();

    private TextField isbnInput = new TextField();
    private TextField filterText = new TextField();

    private Label label;

    private Button search;
    private Button cancel;

    private Grid<Book> bookGrid = new Grid<>(Book.class);

    private BookDbService bookDbService;
    private IsbndbFacade isbndbFacade;

    public BookListView(BookDbService bookDbService, IsbndbFacade isbndbFacade) {
        this.bookDbService = bookDbService;
        this.isbndbFacade = isbndbFacade;

        addClassName("book-list-view");
        setSizeFull();

        bookGrid.addClassName("content-grid");
        HorizontalLayout content = new HorizontalLayout(bookGrid);
        content.setSizeFull();

        add(getToolBar(), content);

        configureGrid();
        configureIsbnInputPopupWindow();

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

        bookGrid.asSingleSelect().addValueChangeListener(e -> editBook(e.getValue()));
    }

    private void configureFilterText() {
        filterText.setPlaceholder("Wpisz tytuł lub autora...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateBookList());
    }

    private void configureIsbnInputPopupWindow() {
        search = new Button("Szukaj");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        search.addClickShortcut(Key.ENTER);
        search.addClickListener(e -> searchBookByIsbn());

        cancel = new Button("Anuluj");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);
        cancel.addClickListener(e -> cancelIsbnSearch());

        isbnInput.setAutofocus(true);
        isbnInput.setPlaceholder("Wpisz nr isbn...");
        isbnInput.setClearButtonVisible(true);
        isbnInput.addValueChangeListener(e -> validateIsbnInputValue(e.getValue()));
        isbnInput.setValueChangeMode(ValueChangeMode.EAGER);

        label = new Label("");
        label.getElement().getStyle().set("color", "red");
        label.getElement().getStyle().set("font-size", "10px");
        label.setVisible(false);
        HorizontalLayout buttons = new HorizontalLayout(search, cancel);
        VerticalLayout layout = new VerticalLayout(label, isbnInput, buttons);
        isbnInputPopupWindow.add(layout);
    }

    private void validateIsbnInputValue(String value) {
        boolean validateResultIsbn = Pattern.matches("\\d{10}+", value);
        boolean validateResultIsbn13 = Pattern.matches("\\d{13}", value);
        if (validateResultIsbn || validateResultIsbn13) {
            label.setVisible(false);
            search.setEnabled(true);
        } else {
            label.setVisible(true);
            label.setText("Numer powienien być 10 lub 13-cyfrowy.");
            search.setEnabled(false);
        }
    }

    private void configureBookViewPopupWindow() {
        bookViewPopupWindow.setHeight("675px");
        bookViewPopupWindow.setWidth("800px"); // UWAGA! Przy zwiększeniu do 900px formularz kurczy się w oknie dialogowym
    }

    private BookForm getBookForm(Book book) {
        bookForm = new BookForm();
        //bookForm.addClassName("editing");
        bookForm.setBook(book);
        bookForm.addListener(BookForm.SaveBookEvent.class, this::saveBook);
        bookForm.addListener(BookForm.DeleteBookEvent.class, this::deleteBook);
        bookForm.addListener(BookForm.CloseEvent.class, e -> closeBookForm());
        bookForm.setSizeFull();
        return bookForm;
    }

    private void getBookViewPopupWindow(Book book) {
        configureBookViewPopupWindow();
        HorizontalLayout dialog = new HorizontalLayout(getBookCoverImage(book), getBookForm(book));
        dialog.setSizeFull();
        bookViewPopupWindow.add(dialog);
        bookViewPopupWindow.addDialogCloseActionListener(e -> closeBookForm());
        bookViewPopupWindow.open();
    }

    private HorizontalLayout getToolBar() {
        configureFilterText();
        Button addBookButton = new Button("Dodaj nowy rekord");
        addBookButton.addClickListener(click -> isbnInputPopupWindow.open());
        HorizontalLayout toolBar = new HorizontalLayout(filterText, addBookButton);
        return toolBar;
    }

    public Image getBookCoverImage(Book book) {
        if(book.getTitle() != null) {
            Image bookCover = new Image();
            bookCover.setSrc(book.getImage());
            bookCover.setAlt("Book's cover");
            bookCover.setMaxWidth("300px");
            bookCover.setMaxHeight("300px");
            bookCover.setMinWidth("200px");
            bookCover.setMinHeight("200px");
            return bookCover;
        }
        return new Image();
    }

    private void addNewBook(Book book) {
        LOGGER.info("addNewBook() method was called...");
        bookGrid.asSingleSelect().clear();
        editBook(book);
    }

    private void editBook(Book book) {
        if(book == null) {
            closeBookForm();
            LOGGER.info("closeBookForm() was called by editBook(Book book) and book value was " + book + " / " + book.toString());
        } else {
            getBookViewPopupWindow(book);
            LOGGER.info("getBookViewPopupWindow(book) method was called by editBook(Book book) and book value was " + book.toString());
            //bookForm.addClassName("editing");
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
        bookViewPopupWindow.removeAll();
        bookViewPopupWindow.close();
        updateBookList();
        //bookForm.addClassName("editing");
    }

    public void searchBookByIsbn() {
        String bookIsbn = getIsbnInput().getValue();
        Book book = isbndbFacade.getBook(bookIsbn);
        isbnInput.clear();
        label.setVisible(false);
        isbnInputPopupWindow.close();
        LOGGER.info("book.equals(new Book()) = " + book.equals(new Book()));
        LOGGER.info("book = " + book.toString());
        LOGGER.info("new Book()) = " + new Book().toString());
        if(book.equals(new Book())) {
            addNewBook(getBookWithIsbn(bookIsbn));
            LOGGER.info("addNewBook() was called by searchBookByIsbn() method and book value " + book.toString());
        } else {
            editBook(book);
            LOGGER.info("editBook(book); was called by searchBookByIsbn() method and book value " + book.toString());
        }
    }

    public void cancelIsbnSearch() {
        isbnInputPopupWindow.close();
    }

    public Book getBookWithIsbn (String isbnNumber) {
        if (isbnNumber.length() == 10) {
            return new Book(isbnNumber, null);
        } else if (isbnNumber.length() == 13){
            return new Book(null, isbnNumber);
        }
        return new Book();
    }
}

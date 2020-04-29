package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;

public class BookForm extends FormLayout {

    private final TextField isbn = new TextField("ISBN");
    private final TextField isbn13 = new TextField("ISBN13");
    private final TextField title = new TextField("Tytuł");
    private final TextField publisher = new TextField("Wydawnictwo");
    private final TextArea synopsys = new TextArea("Streszczenie");
    private final TextField image = new TextField("Link");
    private final TextField authors = new TextField("Autorzy");
    private final TextArea subjects = new TextArea("Kategorie");
    private final TextField publishDate = new TextField("Rok wydania");

    private Button addBook = new Button("Zapisz");
    private Button deleteBook = new Button("Usuń");
    private Button close = new Button("Anuluj");

    private Binder<Book> binder = new BeanValidationBinder<>(Book.class);

    public BookForm() {
        addClassName("book-form");

        binder.bind(isbn, "isbn");
        binder.bind(isbn13, "isbn13");
        binder.bind(title, "title");
        binder.bind(publisher, "publisher");
        binder.bind(synopsys, "synopsys");
        binder.bind(image, "image");
        binder.bind(authors, "authors");
        binder.bind(subjects, "subjects");
        binder.forField(publishDate)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Wprowadź liczbę!"))
                .bind("publishDate");

        Div bookFormDiv = new Div(createBookForm(), createButtonsLayout());
        bookFormDiv.setSizeFull();
        bookFormDiv.addClassName("book-form-div");

        setSizeFull();

        add(bookFormDiv);
    }

    public void setBook (Book book) {
        binder.setBean(book);
    }

    private Component createButtonsLayout() {
        addBook.setClassName("add-book-button");
        addBook.setEnabled(true);
        addBook.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        deleteBook.setClassName("delete-book-button");
        deleteBook.addThemeVariants(ButtonVariant.LUMO_ERROR);

        close.setClassName("close-button");
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        addBook.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        addBook.addClickListener(click -> validateAndSave());
        deleteBook.addClickListener(click -> fireEvent(new DeleteBookEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        HorizontalLayout buttonsLayout = new HorizontalLayout(addBook, deleteBook, close);
        buttonsLayout.getElement().getStyle().set("padding", "0px 16px 0px 16px");
        buttonsLayout.addClassName("buttons-layout");

        return buttonsLayout;
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveBookEvent(this, binder.getBean()));
        }
    }

    private Component createBookForm() {

        isbn.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        isbn.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        isbn13.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        isbn13.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        title.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        title.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        publisher.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        publisher.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        synopsys.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        synopsys.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        image.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        image.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        authors.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        authors.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        subjects.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        subjects.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        publishDate.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        publishDate.getElement().getStyle().set("padding", "1px 0px 0px 0px");

        isbn.setWidthFull();
        isbn13.setWidthFull();
        title.setWidthFull();
        publisher.setWidthFull();
        synopsys.setWidthFull();
        image.setWidthFull();
        authors.setWidthFull();
        subjects.setWidthFull();
        publishDate.setWidthFull();

        synopsys.getStyle().set("maxHeight", "100px");
        subjects.getStyle().set("maxHeight", "100px");

        return new VerticalLayout(isbn, isbn13, title, publisher, synopsys, image, authors, subjects, publishDate);
    }

    public static abstract class BookFormEvent extends ComponentEvent<BookForm> {
        private Book book;

        public BookFormEvent(BookForm source, Book book) {
            super(source, false);
            this.book = book;
        }

        public Book getBook() {
            return book;
        }
    }

    public static class SaveBookEvent extends BookFormEvent {
        public SaveBookEvent(BookForm source, Book book) {
            super(source, book);
        }
    }

    public static class DeleteBookEvent extends BookFormEvent {
        public DeleteBookEvent(BookForm source, Book book) {
            super(source, book);
        }
    }

    public static class CloseEvent extends BookFormEvent {
        public CloseEvent(BookForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

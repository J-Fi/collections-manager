package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.Book;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class BookForm extends FormLayout {

    private final TextField isbn = new TextField("ISBN");
    private final TextField isbn13 = new TextField("ISBN13");
    private final TextField title = new TextField("Tytuł");
    private final TextField publisher = new TextField("Wydawnictwo");
    private final TextField synopsys = new TextField("Streszczenie");
    private final TextField image = new TextField("Link");
    private final TextField authors = new TextField("Autorzy");
    private final TextField subjects = new TextField("Kategorie");
    private final TextField publishDate = new TextField("Rok wydania");

    private Button addBook = new Button("Dodaj");
    private Button deleteBook = new Button("Usuń");
    private Button close = new Button("Anuluj");

    private Binder<Book> binder;

    public BookForm() {
        addClassName("book-form");

        Div bookFormDiv = new Div(createBookForm(), createButtonsLayout());
        bookFormDiv.addClassName("book-form-div");

        add(bookFormDiv);

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

        /*addBook.addClickListener();
        deleteBook.addClickListener();
        close.addClickListener();*/

        HorizontalLayout buttonsLayout = new HorizontalLayout(addBook, deleteBook, close);
        buttonsLayout.getElement().getStyle().set("padding", "0px 16px 0px 16px");
        buttonsLayout.addClassName("buttons-layout");

        return buttonsLayout;
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

        return new VerticalLayout(isbn, isbn13, title, publisher, synopsys, image, authors, subjects, publishDate);
    }

}

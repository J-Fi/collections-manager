package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.Book;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookFullView extends FormLayout {

    private Book book;

    private final TextField isbn = new TextField("Numer ISBN");
    private final TextField isbn13 = new TextField("Numer ISBN13");
    private final TextField title = new TextField("Tytuł");
    private final TextField publisher = new TextField("Wydawca");
    private final TextArea synopsys = new TextArea("Omówienie");
    private final TextField image = new TextField("Link do zdjęcia okładki");
    private final TextField authors = new TextField("Autor");
    private final TextArea subjects = new TextArea("Treść");
    private final TextField publishDate = new TextField("Data publikacji");

    private Button saveBook = new Button("Zapisz");
    private Button deleteBook = new Button("Zapisz");
    private Button closeView = new Button("Zamknij");

    private Binder<Book> binder = new BeanValidationBinder<>(Book.class);

    public BookFullView(Book book) {
        this.book = book;

        addClassName("book-full-view");

        binder.bind(isbn, "isbn");
        binder.bind(isbn13, "isbn13");
        binder.bind(title, "title");
        binder.bind(publisher, "publisher");
        binder.bind(synopsys, "synopsis");
        binder.bind(image, "image");
        binder.bind(authors, "authors");
        binder.bind(subjects, "subjects");
        binder.forField(publishDate)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Wprowadź liczbę."))
                .bind("publishDate");

        add(getBookCoverImage(), createBookForm(), createButtonsLayout());
    }

    public void setBook(Book book) {
        binder.setBean(book);
    }

    private Component createButtonsLayout() {
        saveBook.setClassName("save-book-button");
        saveBook.setEnabled(true);
        saveBook.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        deleteBook.setClassName("delete-book-button");
        deleteBook.addThemeVariants(ButtonVariant.LUMO_ERROR);

        closeView.setClassName("close-button");
        closeView.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveBook.addClickShortcut(Key.ENTER);
        closeView.addClickShortcut(Key.ESCAPE);

        saveBook.addClickListener(click -> validateAndSave());
        deleteBook.addClickListener(click -> fireEvent(new DeleteBookEvent(this, binder.getBean())));
        closeView.addClickListener(click -> fireEvent(new CloseBookViewEvent(this)));

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveBook, deleteBook, closeView);
        buttonsLayout.getElement().getStyle().set("padding", "0px 16px 0px 16px");
        buttonsLayout.addClassName("buttons-layout");

        return buttonsLayout;
    }

    public void validateAndSave() {
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
        synopsys.setMinHeight("100px");
        image.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        image.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        authors.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        authors.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        subjects.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        subjects.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        subjects.setMinHeight("100px");
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

    public Image getBookCoverImage() {
        Image bookCover = new Image();
        bookCover.setSrc(book.getImage());
        bookCover.setAlt("Book's cover");
        bookCover.setMaxWidth("300px");
        bookCover.setMaxHeight("300px");
        bookCover.setMinWidth("200px");
        bookCover.setMinHeight("200px");
        return bookCover;
    }

    public static abstract class BookFullViewEvent extends ComponentEvent<BookFullView> {
        private Book book;

        public BookFullViewEvent(BookFullView source, Book book) {
            super(source, false);
            this.book = book;
        }

        public Book getBook() {
            return book;
        }
    }

    public static class SaveBookEvent extends BookFullViewEvent {
        public SaveBookEvent(BookFullView source, Book book) {
            super(source, book);
        }
    }

    public static class DeleteBookEvent extends BookFullViewEvent {
        public DeleteBookEvent(BookFullView source, Book book) {
            super(source, book);
        }
    }

    public static class CloseBookViewEvent extends BookFullViewEvent {
        public CloseBookViewEvent(BookFullView source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

/*    public BookToBackendDto getBookToBackendDto() {
        System.out.println("SingleBookDialogView get() bookId: " + bookId);
        return new BookToBackendDto(bookId, isbn.getTextContent(), isbn13.getTextContent(),
                title.getTextContent(), publisher.getTextContent(),
                synopsys.getTextContent(), image.getTextContent(),
                authors.getTextContent(), subjects.getTextContent(),
                publishDate.getTextContent(), booksCollectionId);
    }*/
}

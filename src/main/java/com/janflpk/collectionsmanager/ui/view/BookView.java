package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.Book;
import com.janflpk.collectionsmanager.ui.components.LabelledText;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@Route(layout = MainLayout.class) //value = "book",
public class BookView extends HorizontalLayout {

    private Book book;
    private String bookId;
    private String booksCollectionId;

    private final LabelledText isbn = new LabelledText();
    private final LabelledText isbn13 = new LabelledText();
    private final LabelledText title = new LabelledText();
    private final LabelledText publisher = new LabelledText();
    private final LabelledText synopsys = new LabelledText();
    private final LabelledText image = new LabelledText();
    private final LabelledText authors = new LabelledText();
    private final LabelledText subjects = new LabelledText();
    private final LabelledText publishDate = new LabelledText();


    //private Binder<BookToBackendDto> binder;
    //private BookToBackendDto bookToBackendDto;


    public BookView(Book book) {
        this.book = book;
        //this.bookToBackendDto = bookToBackendDto;
        //this.binder = new Binder<>(BookToBackendDto.class);

        //binder.bindInstanceFields(this);
        //bookCover = new Image(image.getValue(), "Book's cover");



        //HorizontalLayout layout = new HorizontalLayout(bookCover, layoutV);
        //layoutV.setHeight("100%");
        //layout.setWidth("100%");
        //layoutV.setSpacing(false);

        //layoutV.setSizeFull();
        //layout.setSizeFull();
/*        title.setSizeFull();
        authors.setSizeFull();
        publishDate.setSizeFull();
        publisher.setSizeFull();
        subjects.setSizeFull();
        isbn.setSizeFull();
        isbn13.setSizeFull();
        synopsys.setSizeFull();*/
/*        VerticalLayout synopsysInfo = new VerticalLayout(synopsys);
        //HorizontalLayout horizontal = new HorizontalLayout(shortInfo);
        VerticalLayout mainLayout = new VerticalLayout(shortInfo, synopsysInfo);*/

        add(getBookCoverImage(), getBookDetails());
    }

    public VerticalLayout getBookDetails() {
        VerticalLayout layout = new VerticalLayout (title, authors, publishDate, publisher, subjects, isbn, isbn13, synopsys);
        //bookCover.setDescriptorText("Tytuł: ");

        //this.bookId = bookToBackendDto.getBookId();
        //this.booksCollectionId = bookToBackendDto.getBooksCollectionId();

        title.setDescriptorText("Tytuł: ");
        title.setTextContent(book.getTitle());

        authors.setDescriptorText("Autorzy: ");
        authors.setTextContent(book.getAuthors());

        publishDate.setDescriptorText("Rok wydania: ");
        publishDate.setTextContent(book.getPublishDate().toString());

        publisher.setDescriptorText("Wydawnictwo: ");
        publisher.setTextContent(book.getPublisher());

        subjects.setDescriptorText("Kategorie: ");
        subjects.setTextContent(book.getSubjects());

        isbn.setDescriptorText("ISBN: ");
        isbn.setTextContent(book.getIsbn());

        isbn13.setDescriptorText("ISBN13: ");
        isbn13.setTextContent(book.getIsbn13());

        synopsys.setDescriptorText("Streszczenie: ");
        synopsys.setTextContent(book.getSynopsys());

        image.setDescriptorText("Link do zdjęcia: ");
        image.setTextContent(book.getImage());

        return layout;

        //binder.setBean(bookToBackendDto);
/*        if (bookToBackendDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //isbn.focus();
        }*/
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

/*    public BookToBackendDto getBookToBackendDto() {
        System.out.println("SingleBookDialogView get() bookId: " + bookId);
        return new BookToBackendDto(bookId, isbn.getTextContent(), isbn13.getTextContent(),
                title.getTextContent(), publisher.getTextContent(),
                synopsys.getTextContent(), image.getTextContent(),
                authors.getTextContent(), subjects.getTextContent(),
                publishDate.getTextContent(), booksCollectionId);
    }*/
}

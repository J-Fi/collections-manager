package com.janflpk.collectionsmanager.backend.domain.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("isbn13")
    private String isbn13;

    @JsonProperty("title")
    private String title;

    @JsonProperty("publisher")
    private String publisher;

    @Lob
    @JsonProperty("synopsys")
    private String synopsys;

    @JsonProperty("image")
    private String image;

    @Lob
    @JsonProperty("authors")
    private List<Author> authors;

    @Lob
    @Nullable
    @JsonProperty("subjects")
    private List<Subject> subjects;

    @JsonProperty("date_published")
    private Integer publishDate;

    @Override
    public String toString() {
        return "BookDto{" +
                "isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", synopsys='" + synopsys + '\'' +
                ", image='" + image + '\'' +
                ", authors=" + authors +
                ", subjects=" + subjects +
                ", publishDate=" + publishDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;

        BookDto bookDto = (BookDto) o;

        if (getIsbn() != null ? !getIsbn().equals(bookDto.getIsbn()) : bookDto.getIsbn() != null) return false;
        if (getIsbn13() != null ? !getIsbn13().equals(bookDto.getIsbn13()) : bookDto.getIsbn13() != null) return false;
        if (getTitle() != null ? !getTitle().equals(bookDto.getTitle()) : bookDto.getTitle() != null) return false;
        if (getPublisher() != null ? !getPublisher().equals(bookDto.getPublisher()) : bookDto.getPublisher() != null)
            return false;
        if (getSynopsys() != null ? !getSynopsys().equals(bookDto.getSynopsys()) : bookDto.getSynopsys() != null)
            return false;
        if (getImage() != null ? !getImage().equals(bookDto.getImage()) : bookDto.getImage() != null) return false;
        if (getAuthors() != null ? !getAuthors().equals(bookDto.getAuthors()) : bookDto.getAuthors() != null)
            return false;
        if (getSubjects() != null ? !getSubjects().equals(bookDto.getSubjects()) : bookDto.getSubjects() != null)
            return false;
        return getPublishDate() != null ? getPublishDate().equals(bookDto.getPublishDate()) : bookDto.getPublishDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getIsbn() != null ? getIsbn().hashCode() : 0;
        result = 31 * result + (getIsbn13() != null ? getIsbn13().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getPublisher() != null ? getPublisher().hashCode() : 0);
        result = 31 * result + (getSynopsys() != null ? getSynopsys().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getAuthors() != null ? getAuthors().hashCode() : 0);
        result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
        result = 31 * result + (getPublishDate() != null ? getPublishDate().hashCode() : 0);
        return result;
    }
}

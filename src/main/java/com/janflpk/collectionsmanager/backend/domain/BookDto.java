package com.janflpk.collectionsmanager.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;
import javax.security.auth.Subject;
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
}

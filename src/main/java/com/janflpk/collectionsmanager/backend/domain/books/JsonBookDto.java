package com.janflpk.collectionsmanager.backend.domain.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonBookDto {

    @JsonProperty("book")
    private BookDto bookDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonBookDto)) return false;

        JsonBookDto that = (JsonBookDto) o;

        return getBookDto() != null ? getBookDto().equals(that.getBookDto()) : that.getBookDto() == null;
    }

    @Override
    public int hashCode() {
        return getBookDto() != null ? getBookDto().hashCode() : 0;
    }
}

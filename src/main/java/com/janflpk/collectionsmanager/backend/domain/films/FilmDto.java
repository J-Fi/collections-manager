package com.janflpk.collectionsmanager.backend.domain.films;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDto {

    @JsonProperty("Title")
    private String filmTitle;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Director")
    private String directorName;

    @JsonProperty("Writer")
    private String writers;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Language")
    private String language;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Poster")
    private String posterLink;

    @JsonProperty("Production")
    private String production;
}

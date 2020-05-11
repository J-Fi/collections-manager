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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmDto)) return false;

        FilmDto filmDto = (FilmDto) o;

        if (getFilmTitle() != null ? !getFilmTitle().equals(filmDto.getFilmTitle()) : filmDto.getFilmTitle() != null)
            return false;
        if (getYear() != null ? !getYear().equals(filmDto.getYear()) : filmDto.getYear() != null) return false;
        if (getRuntime() != null ? !getRuntime().equals(filmDto.getRuntime()) : filmDto.getRuntime() != null)
            return false;
        if (getDirectorName() != null ? !getDirectorName().equals(filmDto.getDirectorName()) : filmDto.getDirectorName() != null)
            return false;
        if (getWriters() != null ? !getWriters().equals(filmDto.getWriters()) : filmDto.getWriters() != null)
            return false;
        if (getActors() != null ? !getActors().equals(filmDto.getActors()) : filmDto.getActors() != null) return false;
        if (getPlot() != null ? !getPlot().equals(filmDto.getPlot()) : filmDto.getPlot() != null) return false;
        if (getLanguage() != null ? !getLanguage().equals(filmDto.getLanguage()) : filmDto.getLanguage() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(filmDto.getCountry()) : filmDto.getCountry() != null)
            return false;
        if (getPosterLink() != null ? !getPosterLink().equals(filmDto.getPosterLink()) : filmDto.getPosterLink() != null)
            return false;
        return getProduction() != null ? getProduction().equals(filmDto.getProduction()) : filmDto.getProduction() == null;
    }

    @Override
    public int hashCode() {
        int result = getFilmTitle() != null ? getFilmTitle().hashCode() : 0;
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getRuntime() != null ? getRuntime().hashCode() : 0);
        result = 31 * result + (getDirectorName() != null ? getDirectorName().hashCode() : 0);
        result = 31 * result + (getWriters() != null ? getWriters().hashCode() : 0);
        result = 31 * result + (getActors() != null ? getActors().hashCode() : 0);
        result = 31 * result + (getPlot() != null ? getPlot().hashCode() : 0);
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getPosterLink() != null ? getPosterLink().hashCode() : 0);
        result = 31 * result + (getProduction() != null ? getProduction().hashCode() : 0);
        return result;
    }
}

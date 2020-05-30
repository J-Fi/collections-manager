package com.janflpk.collectionsmanager.backend.domain.films;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(
                name = "Film.findAll",
                query = "FROM Film WHERE lower(filmTitle) like (concat('%', :searchText, '%')) or " +
                        "lower(directorName) like (concat('%', :searchText, '%'))"),
        @NamedQuery(
                name = "Film.findByFilmsCollectionId",
                query = "FROM Film WHERE films_collection_id = :filmsCollectionId and " +
                        "(lower(filmTitle) like (concat('%', :searchText, '%')) or " +
                        "lower(directorName) like (concat('%', :searchText, '%')))"
        )
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FILMS")
public class Film {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id", unique = true)
    private Long filmId;

    @Column(name = "Title")
    private String filmTitle;

    @Column(name = "Year")
    private String year;

    @Column(name = "Runtime")
    private String runtime;

    @Column(name = "Director")
    private String directorName;

    @Column(name = "Writer", length = 65535, columnDefinition = "text")
    private String writers;

    @Column(name = "Actors", length = 65535, columnDefinition = "text")
    private String actors;

    @Column(name = "Plot", length = 65535, columnDefinition = "text")
    private String plot;

    @Column(name = "Language")
    private String language;

    @Column(name = "Country")
    private String country;

    @Column(name = "Poster")
    private String posterLink;

    @Column(name = "Production")
    private String production;

    @ManyToOne
    @JoinColumn(name = "films_collection_id")
    private FilmsCollection filmsCollection;

    public Film(String filmTitle, String year, String runtime, String directorName, String writers, String actors,
                String plot, String language, String country, String posterLink, String production) {
        this.filmTitle = filmTitle;
        this.year = year;
        this.runtime = runtime;
        this.directorName = directorName;
        this.writers = writers;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.posterLink = posterLink;
        this.production = production;
    }

    public Film(String filmTitle, String year, String runtime, String directorName, String writers, String actors,
                String plot, String language, String country, String posterLink, String production, FilmsCollection filmsCollection) {
        this.filmTitle = filmTitle;
        this.year = year;
        this.runtime = runtime;
        this.directorName = directorName;
        this.writers = writers;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.posterLink = posterLink;
        this.production = production;
        this.filmsCollection = filmsCollection;
    }

    public Film(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmTitle='" + filmTitle + '\'' +
                ", year='" + year + '\'' +
                ", runtime='" + runtime + '\'' +
                ", directorName='" + directorName + '\'' +
                ", writers='" + writers + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", posterLink='" + posterLink + '\'' +
                ", production='" + production + '\'' +
                ", filmsCollection=" + filmsCollection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;

        Film film = (Film) o;

        if (getFilmId() != null ? !getFilmId().equals(film.getFilmId()) : film.getFilmId() != null) return false;
        if (getFilmTitle() != null ? !getFilmTitle().equals(film.getFilmTitle()) : film.getFilmTitle() != null)
            return false;
        if (getYear() != null ? !getYear().equals(film.getYear()) : film.getYear() != null) return false;
        if (getRuntime() != null ? !getRuntime().equals(film.getRuntime()) : film.getRuntime() != null) return false;
        if (getDirectorName() != null ? !getDirectorName().equals(film.getDirectorName()) : film.getDirectorName() != null)
            return false;
        if (getWriters() != null ? !getWriters().equals(film.getWriters()) : film.getWriters() != null) return false;
        if (getActors() != null ? !getActors().equals(film.getActors()) : film.getActors() != null) return false;
        if (getPlot() != null ? !getPlot().equals(film.getPlot()) : film.getPlot() != null) return false;
        if (getLanguage() != null ? !getLanguage().equals(film.getLanguage()) : film.getLanguage() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(film.getCountry()) : film.getCountry() != null) return false;
        if (getPosterLink() != null ? !getPosterLink().equals(film.getPosterLink()) : film.getPosterLink() != null)
            return false;
        if (getProduction() != null ? !getProduction().equals(film.getProduction()) : film.getProduction() != null)
            return false;
        return getFilmsCollection() != null ? getFilmsCollection().equals(film.getFilmsCollection()) : film.getFilmsCollection() == null;
    }

    @Override
    public int hashCode() {
        int result = getFilmId() != null ? getFilmId().hashCode() : 0;
        result = 31 * result + (getFilmTitle() != null ? getFilmTitle().hashCode() : 0);
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
        result = 31 * result + (getFilmsCollection() != null ? getFilmsCollection().hashCode() : 0);
        return result;
    }
}

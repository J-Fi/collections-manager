package com.janflpk.collectionsmanager.backend.domain.films;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}

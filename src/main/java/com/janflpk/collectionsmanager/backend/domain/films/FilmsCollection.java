package com.janflpk.collectionsmanager.backend.domain.films;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FILMS_COLLECTIONS")
public class  FilmsCollection {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "collection_id", unique = true)
    private Long filmsCollectionId;

    @Column(name = "collection_name")
    private String collectionName;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            targetEntity = Film.class,
            mappedBy = "filmsCollection",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Film> films = new ArrayList<>();

    public FilmsCollection(Long filmsCollectionId, String collectionName) {
        this.filmsCollectionId = filmsCollectionId;
        this.collectionName = collectionName;
    }

    public FilmsCollection(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmsCollection)) return false;

        FilmsCollection that = (FilmsCollection) o;

        if (getFilmsCollectionId() != null ? !getFilmsCollectionId().equals(that.getFilmsCollectionId()) : that.getFilmsCollectionId() != null)
            return false;
        if (getCollectionName() != null ? !getCollectionName().equals(that.getCollectionName()) : that.getCollectionName() != null)
            return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        return getFilms() != null ? getFilms().equals(that.getFilms()) : that.getFilms() == null;
    }

    @Override
    public int hashCode() {
        int result = getFilmsCollectionId() != null ? getFilmsCollectionId().hashCode() : 0;
        result = 31 * result + (getCollectionName() != null ? getCollectionName().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getFilms() != null ? getFilms().hashCode() : 0);
        return result;
    }
}

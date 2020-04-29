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

    public FilmsCollection(String collectionName) {
        this.collectionName = collectionName;
    }
}

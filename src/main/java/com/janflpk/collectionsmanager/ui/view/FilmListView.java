package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.omdb.facade.OmdbFacade;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@NoArgsConstructor
@Route(value = "films", layout = MainLayout.class)
public class FilmListView extends VerticalLayout {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmListView.class);

    private FilmForm filmForm;

    private Dialog omdbInputPopupWindow = new Dialog();
    private Dialog filmViewPopupWindow = new Dialog();

    private TextField filmFilterText = new TextField();
    private TextField omdbInput = new TextField();

    private Button search;
    private Button cancel;

    private FilmDbService filmDbService;
    private OmdbFacade omdbFacade;

    Grid<Film> filmGrid = new Grid<>(Film.class);

    public FilmListView(FilmDbService filmDbService, OmdbFacade omdbFacade) {
        this.filmDbService = filmDbService;
        this.omdbFacade = omdbFacade;

        addClassName("film-list-view");
        setSizeFull();

        HorizontalLayout content = new HorizontalLayout(filmGrid);
        content.setSizeFull();

        add(getToolBar(), content);

        configureFilmGrid();
        configureOmdbInputPopupWindow();

        updateFilmList();
    }

    private void configureFilmGrid() {
        filmGrid.addClassName("film-content-grid");
        filmGrid.setSizeFull();
        filmGrid.setColumns("filmTitle", "directorName", "year");

        filmGrid.getColumnByKey("filmTitle")
                .setFlexGrow(20)
                .setResizable(true);
        filmGrid.getColumnByKey("directorName")
                .setFlexGrow(30)
                .setResizable(true);
        filmGrid.getColumnByKey("year")
                .setWidth("150px");

        filmGrid.asSingleSelect().addValueChangeListener(e -> editFilm(e.getValue()));
    }

    private void configureFilmFilterText() {
        filmFilterText.setPlaceholder("Wpisz tytuł lub reżysera...");
        filmFilterText.setClearButtonVisible(true);
        filmFilterText.setValueChangeMode(ValueChangeMode.LAZY);
        filmFilterText.addValueChangeListener(e -> updateFilmList());
    }

    private void configureOmdbInputPopupWindow() {
        search = new Button("Szukaj");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        search.addClickShortcut(Key.ENTER);
        search.addClickListener(e -> searchFilmByTitle());

        cancel = new Button("Anuluj");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);
        cancel.addClickListener(e -> cancelTitleSearch());

        omdbInput.setAutofocus(true);
        omdbInput.setPlaceholder("Wpisz tytuł...");
        omdbInput.setClearButtonVisible(true);
        omdbInput.setValueChangeMode(ValueChangeMode.EAGER);

        HorizontalLayout buttons = new HorizontalLayout(search, cancel);
        VerticalLayout layout = new VerticalLayout(omdbInput, buttons);
        omdbInputPopupWindow.add(layout);
    }

    private void configureFilmViewPopupWindow() {
        filmViewPopupWindow.setHeight("675px");
        filmViewPopupWindow.setWidth("800px");
    }

    private FilmForm getFilmForm(Film film) {
        filmForm = new FilmForm();
        filmForm.setFilm(film);
        filmForm.addListener(FilmForm.SaveFilmEvent.class, this::saveFilm);
        filmForm.addListener(FilmForm.DeleteFilmEvent.class, this::deleteFilm);
        filmForm.addListener(FilmForm.CloseEvent.class, e -> closeFilmForm());
        filmForm.setSizeFull();
        return filmForm;
    }

    private void getFilmViewPopupWindow(Film film) {
        configureFilmViewPopupWindow();
        HorizontalLayout dialog = new HorizontalLayout(getFilmPosterImage(film), getFilmForm(film));
        dialog.setSizeFull();
        filmViewPopupWindow.add(dialog);
        filmViewPopupWindow.addDialogCloseActionListener(e -> closeFilmForm());
        filmViewPopupWindow.open();
    }

    private HorizontalLayout getToolBar() {
        configureFilmFilterText();
        Button addFilmButton = new Button("Dodaj nowy rekord...");
        addFilmButton.addClickListener(e -> omdbInputPopupWindow.open());
        HorizontalLayout toolBar = new HorizontalLayout(filmFilterText, addFilmButton);
        return toolBar;
    }

    public Image getFilmPosterImage(Film film) {
        if(film.getFilmTitle() != null) {
            Image filmPosterImage = new Image();
            filmPosterImage.setSrc(film.getPosterLink());
            filmPosterImage.setAlt("Film's poster");
            filmPosterImage.setMaxHeight("300px");
            filmPosterImage.setMinHeight("200px");
            filmPosterImage.setMaxWidth("300px");
            filmPosterImage.setMinWidth("200px");
            return filmPosterImage;
        }
        return new Image();
    }

    private void addNewFilm(Film film) {
        LOGGER.info("addNewBook() method was called...");
        filmGrid.asSingleSelect().clear();
        editFilm(film);
    }

    public void editFilm(Film film) {
        if(film == null) {
            closeFilmForm();
        } else {
            getFilmViewPopupWindow(film);
        }
    }

    private void updateFilmList() {
        filmGrid.setItems(filmDbService.findAll(filmFilterText.getValue()));
    }

    private void saveFilm(FilmForm.SaveFilmEvent event) {
        filmDbService.saveFilm(event.getFilm(), 37L);
        updateFilmList();
        closeFilmForm();
    }

    private void deleteFilm(FilmForm.DeleteFilmEvent event) {
        filmDbService.deleteFilm(event.getFilm().getFilmId());
        updateFilmList();
        closeFilmForm();
    }

    private void closeFilmForm() {
        filmForm.setFilm(null);
        filmViewPopupWindow.removeAll();
        filmViewPopupWindow.close();
        updateFilmList();
    }

    public void searchFilmByTitle() {
        String title = getOmdbInput().getValue();
        Film film = omdbFacade.getFilm(title);
        omdbInput.clear();
        omdbInputPopupWindow.close();
        if(film.equals(new Book())) {
            addNewFilm(getFilmWithTitle(title));
        } else {
            editFilm(film);
        }
    }

    public void cancelTitleSearch() {
        omdbInputPopupWindow.close();
    }

    public Film getFilmWithTitle(String filmTitle) {
        return new Film(filmTitle);
    }
}

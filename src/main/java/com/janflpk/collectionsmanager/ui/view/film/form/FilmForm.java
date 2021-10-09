package com.janflpk.collectionsmanager.ui.view.film.form;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

@Getter
public class FilmForm extends FormLayout {

    private static final Integer YEAR_LENGTH_4 = 4;

    private final TextField filmTitle = new TextField("Tytuł filmu");
    private final TextField year = new TextField("Rok produkcji");
    private final TextField runtime = new TextField("Czas trwania");
    private final TextField directorName = new TextField("Reżyseria");
    private final TextArea writers = new TextArea("Scenariusz");
    private final TextArea actors = new TextArea("Obsada");
    private final TextArea plot = new TextArea("Treść filmu");
    private final TextField language = new TextField("Język filmu");
    private final TextField country = new TextField("Kraj produkcji");
    private final TextField posterLink = new TextField("Plakat");
    private final TextField production = new TextField("Produkcja");

    private Button addFilm = new Button("Dodaj film");
    private Button deleteFilm = new Button("Usuń");
    private Button close = new Button("Anuluj");

    private Binder<Film> binder = new BeanValidationBinder<>(Film.class);

    public FilmForm() {
        addClassName("film-form");

        binder.bindInstanceFields(this);

        Div filmFormDiv = new Div(createFilmForm(), createButtonsLayout());
        filmFormDiv.setSizeFull();
        filmFormDiv.addClassName("film-form-div");

        setResponsiveSteps(new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490", 2, ResponsiveStep.LabelsPosition.TOP));

        setSizeFull();

        setMaxWidth("500px");

        add(filmFormDiv);
    }

    public void setFilm(Film film) {
        binder.setBean(film);
    }

    private Component createButtonsLayout() {
        addFilm.setClassName("add-film-button");
        addFilm.setEnabled(true);
        addFilm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addFilm.addClickShortcut(Key.ENTER);

        deleteFilm.setClassName("delete-film-button");
        deleteFilm.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteFilm.addClickShortcut(Key.DELETE);

        close.setClassName("close-button");
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickShortcut(Key.ESCAPE);

        addFilm.addClickListener(clock -> validateAndSave());
        deleteFilm.addClickListener(click -> fireEvent(new DeleteFilmEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        HorizontalLayout buttonsLayout = new HorizontalLayout(addFilm, deleteFilm, close);
        buttonsLayout.getElement().getStyle().set("padding", "0px 16px 0px 16px");
        buttonsLayout.setClassName("film-buttons-layout");



        return buttonsLayout;
    }

    public void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveFilmEvent(this, binder.getBean()));
        }
    }
    private Component createFilmForm() {

        filmTitle.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        filmTitle.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        year.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        year.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        runtime.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        runtime.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        directorName.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        directorName.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        writers.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        writers.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        actors.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        actors.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        plot.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        plot.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        language.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        language.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        country.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        country.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        posterLink.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        posterLink.getElement().getStyle().set("padding", "1px 0px 0px 0px");
        production.getElement().getStyle().set("margin", "1px 0px 0px 0px");
        production.getElement().getStyle().set("padding", "1px 0px 0px 0px");

        filmTitle.setWidthFull();
        year.setWidthFull();
        runtime.setWidthFull();
        directorName.setWidthFull();
        writers.setWidthFull();
        actors.setWidthFull();
        plot.setWidthFull();
        language.setWidthFull();
        country.setWidthFull();
        posterLink.setWidthFull();
        production.setWidthFull();

        writers.getStyle().set("maxHeight", "100px");
        actors.getStyle().set("maxHeight", "100px");
        plot.getStyle().set("maxHeight", "100px");

        filmTitle.setAutofocus(true);

        return new VerticalLayout(filmTitle, year, runtime, directorName, writers, actors, plot, language,
                country, posterLink, production);
    }

    public static abstract class FilmFormEvent extends ComponentEvent<FilmForm> {
        private Film film;

        public FilmFormEvent (FilmForm source, Film film) {
            super(source, false);
            this.film = film;
        }

        public Film getFilm() {
            return film;
        }
    }

    public static class SaveFilmEvent extends FilmFormEvent {
        public SaveFilmEvent(FilmForm source, Film film) {
            super(source, film);
        }
    }

    public static class DeleteFilmEvent extends FilmFormEvent {
        public DeleteFilmEvent(FilmForm source, Film film) {
            super(source, film);
        }
    }

    public static class CloseEvent extends FilmFormEvent {
        public CloseEvent(FilmForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

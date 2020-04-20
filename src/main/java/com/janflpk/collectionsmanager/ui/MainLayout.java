package com.janflpk.collectionsmanager.ui;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Route("home")
public class MainLayout extends AppLayout {

    private static Long userId;
    private User user;
    private Tabs tabs;
    private Anchor home;
    Anchor booksCollection;
    Anchor logout;
    //Div greeting;

    public MainLayout(BackendApiService backendApiService) {
        //greeting = new Div();
        //greeting.setText("Witaj");
        this.backendApiService = backendApiService;
        Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
        home = new Anchor();
        booksCollection = new Anchor();
        logout = new Anchor();
        Tab tabHome = new Tab(home);
        Tab tabBookscollection = new Tab(booksCollection);
        Tab tabLogout = new Tab(logout);

        this.tabs = new Tabs(tabHome, tabBookscollection, tabLogout);

        //add(greeting);

        img.setHeight("44px");
        addToNavbar(img, tabs);
        addToDrawer();

        getElement().addEventListener("search-focus", e -> {
            getElement().getClassList().add("hide-navbar");
        });

        getElement().addEventListener("search-blur", e -> {
            getElement().getClassList().remove("hide-navbar");
        });
    }

/*    @Override
    protected void afterNavigation() {
        super.afterNavigation();*/
        //confirmDialog.setOpened(false);
        /*if (getContent() instanceof HasConfirmation) {
            ((HasConfirmation) getContent()).setConfirmDialog(confirmDialog);
        }*/

/*        String target = RouteConfiguration.forSessionScope().getUrl(this.getContent().getClass());
        Optional<com.vaadin.flow.component.Component> tabToSelect = tabs.getChildren().filter(tab -> {
            com.vaadin.flow.component.Component child = tab.getChildren().findFirst().get();
            return child instanceof Anchor && ((Anchor) child).getHref().equals(target);
        }).findFirst();
        tabToSelect.ifPresent(tab -> tabs.setSelectedTab((Tab)tab));*/
   /* }*/

/*    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.booksCollectionId = parameter;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        refreshBooks(userId);
        refreshFilms(userId);
    }*/

    public User getUser(Long userId) {
        return backendApiService.findUser(userId);
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.userId = parameter;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        home.setHref("home" + "/" + userId);
        home.setText("Home");

        booksCollection.setHref("collections" + "/" + userId);
        booksCollection.setText("Kolekcje");

        logout.setHref("login");
        logout.setText("Wyloguj");

        //greeting.setText("Witaj, " + getUser(userId).getFirstName() + "!");
    }
}

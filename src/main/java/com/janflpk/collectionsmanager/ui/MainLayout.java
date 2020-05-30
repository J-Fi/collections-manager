package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.ui.view.BooksCollectionsListView;
import com.janflpk.collectionsmanager.ui.view.FilmsCollectionsListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout implements RouterLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Personal Collection Manager");
        logo.addClassName("logo");

        Anchor logout = new Anchor("/logout", "Wyloguj");
        logout.addClassName("logout");
        logout.getElement().getStyle().set("margin", "5px");
        logout.getElement().getStyle().set("padding", "5px");

        DrawerToggle drawer = new DrawerToggle();

        HorizontalLayout header = new HorizontalLayout(drawer, logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink bookListsLink = new RouterLink("Kolekcje książek", BooksCollectionsListView.class);
        RouterLink filmListsLink = new RouterLink("Kolekcje filmów", FilmsCollectionsListView.class);

        bookListsLink.setHighlightCondition(HighlightConditions.sameLocation());
        filmListsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(bookListsLink, filmListsLink));
    }
}

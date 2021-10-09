package com.janflpk.collectionsmanager.ui;

import com.janflpk.collectionsmanager.ui.view.BooksCollectionsListView;
import com.janflpk.collectionsmanager.ui.view.FilmsCollectionsListView;
import com.janflpk.collectionsmanager.ui.view.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

@CssImport("./styles/shared-styles.css")
@Viewport("width=device-width")
public class MainLayout extends AppLayout implements RouterLayout, PageConfigurator {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        System.out.println("Does it work??");
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
        RouterLink homeViewLink = new RouterLink("Strona główna", HomeView.class);
        RouterLink bookListsLink = new RouterLink("Kolekcje książek", BooksCollectionsListView.class);
        RouterLink filmListsLink = new RouterLink("Kolekcje filmów", FilmsCollectionsListView.class);

        homeViewLink.setHighlightCondition(HighlightConditions.sameLocation());
        bookListsLink.setHighlightCondition(HighlightConditions.sameLocation());
        filmListsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(homeViewLink, bookListsLink, filmListsLink));
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        System.out.println("configurePage() was called" + settings.toString());
        settings.addLink("shortcut icon", "icons/favicon.ico");
        //settings.addLink("shortcut icon", "icons/favicon.ico");
    }
}

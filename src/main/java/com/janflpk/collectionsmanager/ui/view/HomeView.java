package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    H1 welcomeTextHeader = new H1("Witaj!");
    H2 welcomeTextBody = new H2(" Aplikacja do zarządzania Twoimi kolekcjami czeka na Twoje rozkazy!");

    public HomeView() {

        add(welcomeTextHeader, welcomeTextBody);
    }
}

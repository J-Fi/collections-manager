package com.janflpk.collectionsmanager.ui.view;

import com.janflpk.collectionsmanager.backend.service.CurrentUserRetriever;
import com.janflpk.collectionsmanager.backend.service.UserDbService;
import com.janflpk.collectionsmanager.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout implements AfterNavigationObserver {

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private CurrentUserRetriever currentUserRetriever;

    private H1 welcomeTextHeader = new H1();
    private H2 welcomeTextBody = new H2();

    public HomeView() {

        welcomeTextBody.setText(" Aplikacja do zarządzania Twoimi kolekcjami czeka na Twoje rozkazy!");

        add(welcomeTextHeader, welcomeTextBody);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        Long userId = currentUserRetriever.retrieveUserId();
        String firstName = userDbService.findById(userId).getFirstName();
        welcomeTextHeader.setText("Witaj, " + firstName + "!");
    }
}

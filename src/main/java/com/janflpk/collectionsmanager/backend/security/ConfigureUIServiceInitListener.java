package com.janflpk.collectionsmanager.backend.security;

import com.janflpk.collectionsmanager.ui.view.login.LoginView;
import com.janflpk.collectionsmanager.ui.view.register.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    /*
    Reroute to LoginView if the accessed page is not LoginView and RegisterView.
    Thanks to this it is possible to access RegisterView for users not logged in.
    Otherwise unlogged users will be rerouted to LoginView even if they try to
    access register form.
     */
    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!LoginView.class.equals(event.getNavigationTarget())
            && !RegisterView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}

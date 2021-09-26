package com.janflpk.collectionsmanager.ui.view.register;

import com.janflpk.collectionsmanager.backend.service.UserDbService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("register")
@PageTitle("Collections Manager | Register")
public class RegisterView extends VerticalLayout {

    UserDbService userDbService = new UserDbService();

    public RegisterView() {
        RegisterForm registerForm = new RegisterForm();

        setHorizontalComponentAlignment(Alignment.CENTER, registerForm);

        add(registerForm);

        RegisterFormBinder registerFormBinder = new RegisterFormBinder(registerForm, userDbService);
        registerFormBinder.addBindingAndValidation();
    }
}

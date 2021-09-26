package com.janflpk.collectionsmanager.ui.view.register;

import com.janflpk.collectionsmanager.backend.domain.user.facade.UserFacade;
import com.janflpk.collectionsmanager.backend.mapper.UserMapper;
import com.janflpk.collectionsmanager.backend.service.UserDbService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("register")
@PageTitle("Collections Manager | Register")
public class RegisterView extends VerticalLayout {

    public RegisterView(UserFacade userFacade) {

        RegisterForm registerForm = new RegisterForm();

        setHorizontalComponentAlignment(Alignment.CENTER, registerForm);

        add(registerForm);

        RegisterFormBinder registerFormBinder = new RegisterFormBinder(registerForm, userFacade);
        registerFormBinder.addBindingAndValidation();
    }
}

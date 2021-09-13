package com.janflpk.collectionsmanager.ui.view.register;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.Getter;

import java.util.stream.Stream;

@Route("registration")
@PageTitle("Collections Manager | Registration")
@Getter
public class RegisterView extends FormLayout {

    private H3 title;

    private TextField firstName;
    private TextField lastName;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;

    private Span errorMessageField;

    private Button submitButton;

    public RegisterView() {
        title = new H3("Rejestracja");
        firstName = new TextField("Imię");
        lastName = new TextField("Nazwisko");
        email = new EmailField("Email");

        password = new PasswordField("Hasło");
        passwordConfirm = new PasswordField("Potwierdź hasło");

        setRequiredIndicatorVisible(firstName, lastName, email, password, passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button("Zarejestruj się");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, firstName, lastName, email, password, passwordConfirm, errorMessageField, submitButton);

        setMaxWidth("500px");

        setResponsiveSteps(new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                            new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton,2);
    }

    private void setRequiredIndicatorVisible (HasValueAndElement<?,?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}

package com.janflpk.collectionsmanager.ui.view.register;

import com.janflpk.collectionsmanager.backend.domain.user.UserDto;
import com.janflpk.collectionsmanager.backend.domain.user.facade.UserFacade;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

public class RegisterFormBinder {

    private RegisterForm registerForm;

    private UserFacade userFacade;

    private boolean enablePasswordValidation;

    public RegisterFormBinder(RegisterForm registerForm, UserFacade userFacade) {
        this.registerForm = registerForm;
        this.userFacade = userFacade;
    }

    public void addBindingAndValidation() {
        BeanValidationBinder<UserDto> binder = new BeanValidationBinder<>(UserDto.class);
        binder.bindInstanceFields(registerForm);

        binder.forField(registerForm.getPassword())
                .withValidator(this::passwordValidator).bind("password");

        registerForm.getPasswordConfirm().addValueChangeListener(e -> {
            enablePasswordValidation = true;
            binder.validate();
        });

        binder.setStatusLabel(registerForm.getErrorMessageField());

        registerForm.getSubmitButton().addClickListener(event -> {
            try {
                UserDto userBean = new UserDto();
                binder.writeBean(userBean);
                userFacade.saveUser(userBean);
                showSuccess(userBean);
            } catch (ValidationException exception) {

            }
        });
    }

    private ValidationResult passwordValidator (String pass1, ValueContext ctx) {

        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Hasło musi mieć co najmniej 8 znaków długości.");
        }

        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = registerForm.getPasswordConfirm().getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Hasło nie zgadza się.");
    }

    private void showSuccess(UserDto userBean) {
        Notification notification = Notification.show("Twoje dane zostały zapisane. Witaj, " + userBean.getFirstName());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}

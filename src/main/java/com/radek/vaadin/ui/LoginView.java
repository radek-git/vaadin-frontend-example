package com.radek.vaadin.ui;

import com.radek.vaadin.security.SecurityUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginOverlay login;

    public LoginView() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Vaadin Test App");
        i18n.getHeader().setDescription("...");
        i18n.setAdditionalInformation(null); //dezaktywacja dodatkowego opisu poniżej Description

        login = new LoginOverlay();
        login.setI18n(i18n);
        login.setForgotPasswordButtonVisible(false);
        login.setAction("login");
        login.setOpened(true);

        add(login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityUtils.isUserLoggedIn()) {
            UI.getCurrent().getPage().getHistory().replaceState(null, "");
            event.rerouteTo(AllUsersView.class);
            login.setOpened(false);
        }
    }
}

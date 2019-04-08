package com.radek.vaadin.ui;

import com.radek.vaadin.backend.entity.User;
import com.radek.vaadin.backend.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("add")
public class UsersView extends VerticalLayout {

    private UserService userService;

    private TextField name;
    private TextField surname;
    private TextField username;
    private TextField email;

    private User user = new User();

    @Autowired
    public UsersView(UserService userService) {
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        name = new TextField("name");
        surname = new TextField("surname");
        username = new TextField("username");
        email = new TextField("email");

        Binder<User> binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);
        binder.setBean(user);

        Button save = new Button("save", click -> {
            Notification.show("Valid: " + binder.isValid(), 6000, Notification.Position.BOTTOM_CENTER);
            userService.add(user);
        });

        save.getElement().getThemeList().add("primary");//ustawienie save jako głównego przycisku

//        save.addClickListener(click -> {
//
//        });

        add(name, surname, username, email, save);
    }
}

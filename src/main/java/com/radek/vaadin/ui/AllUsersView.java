package com.radek.vaadin.ui;

import com.radek.vaadin.backend.entity.User;
import com.radek.vaadin.backend.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("all")
public class AllUsersView extends VerticalLayout {

    private UserService userService;

    private Grid<User> grid;

    @Autowired
    public AllUsersView(UserService userService) {
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        grid = new Grid<>();
        grid.setColumnReorderingAllowed(true);
        grid.setItems(userService.findAll());

        grid.addColumn(User::getName).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addColumn(User::getUsername).setHeader("Username");
        grid.addColumn(User::getEmail).setHeader("Email");

        add(grid);
    }
}

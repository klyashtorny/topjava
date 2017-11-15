package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            adminUserController.create(new User(null, "Саша", "email11", "password", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "Петя", "email7", "password", Role.ROLE_USER));
            adminUserController.create(new User(null, "Лена", "email9", "password", Role.ROLE_USER));
            User user = adminUserController.create(new User(null, "Юля", "email13", "password", Role.ROLE_USER));

            System.out.println("--------------user.getAll---------------");
            System.out.println(adminUserController.getAll());

            System.out.println("--------------user.update---------------");
            adminUserController.update(user, 9);

            System.out.println("--------------user.getAll---------------");
            System.out.println(adminUserController.getAll());


        }
    }
}

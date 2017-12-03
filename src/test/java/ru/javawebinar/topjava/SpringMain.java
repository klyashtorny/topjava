package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management

        GenericXmlApplicationContext springContext = new GenericXmlApplicationContext();
        springContext.getEnvironment().setActiveProfiles(Profiles.HSQL_DB, Profiles.JDBC);
        springContext.load("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.refresh();

        System.out.println("Bean definition names: " + Arrays.toString(springContext.getBeanDefinitionNames()));
        AdminRestController adminUserController = springContext.getBean(AdminRestController.class);
        adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
        System.out.println();

        MealRestController mealController = springContext.getBean(MealRestController.class);
        List<MealWithExceed> filteredMealsWithExceeded =
                mealController.getBetween(
                        LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                        LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
        filteredMealsWithExceeded.forEach(System.out::println);

    }
}

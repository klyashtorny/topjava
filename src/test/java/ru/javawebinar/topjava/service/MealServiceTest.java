package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
       Meal mealUser = service.get(USER_MEAL.iterator().next().getId(), USER_ID);
       assertEquals(mealUser, USER_MEAL.iterator().next());
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        Meal mealUser = service.get(USER_MEAL.get(2).getId(), ADMIN_ID);
        assertEquals(mealUser, ADMIN_MEAL.iterator().next());
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_MEAL.get(2).getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void alienDelete() throws Exception {
        service.delete(USER_MEAL.iterator().next().getId(), ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> mealList = service.getAll(USER_ID);
        assertThat(new HashSet<>(mealList)).isEqualTo(new HashSet<>(USER_MEAL));
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(USER_MEAL.get(1));
        updated.setDateTime(LocalDateTime.of(2017, Month.NOVEMBER, 15, 10, 0));
        updated.setCalories(250);
        updated.setDescription("Новая еда");
        service.update(updated, USER_ID);
        assertEquals(service.get(USER_MEAL.get(1).getId(), USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2017, Month.NOVEMBER, 12, 15, 0), "Еда новая", 800);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertEquals(newMeal, service.get(newMeal.getId(), USER_ID));
    }

}
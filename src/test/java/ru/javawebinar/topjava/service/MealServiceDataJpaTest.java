package ru.javawebinar.topjava.service;

import org.junit.Test;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class MealServiceDataJpaTest extends AbstractMealServiceTest {

    @Test
    public void testGetWithUser() throws Exception {
        Meal meal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        UserTestData.assertMatch(meal.getUser(), ADMIN);
        MealTestData.assertMatch(meal, ADMIN_MEAL1);
    }

    @Test
    public void testGetWithUserNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getWithUser(MEAL1_ID, ADMIN_ID);
    }
}

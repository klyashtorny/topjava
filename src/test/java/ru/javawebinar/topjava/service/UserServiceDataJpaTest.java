package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ActiveProfiles(Profiles.DATAJPA)
public class UserServiceDataJpaTest extends AbstractUserServiceTest{

    @Test
    public void testGetWithMeal() throws Exception {
        User user = service.getWithMeal(USER_ID);
        UserTestData.assertMatch(USER, user);
        MealTestData.assertMatch(MealTestData.MEALS, user.getMeals());
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealNotFound() throws Exception {
        service.getWithMeal(1);
    }


}

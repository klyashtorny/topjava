package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Map;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ActiveProfiles(Profiles.DATAJPA)
public class UserServiceDataJpaTest extends AbstractUserServiceTest{

    @Autowired
    private UserService service;

    @Test
    public void testGetWithMeal() throws Exception {
        Map<User, List<Meal>> map = service.getWithMeal(USER_ID);
        User user = map.keySet().iterator().next();
        List<Meal> meals = map.get(user);
        UserTestData.assertMatch(user, USER);
        MealTestData.assertMatch(meals, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }


}

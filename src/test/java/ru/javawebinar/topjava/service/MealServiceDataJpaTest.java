package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL2;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class MealServiceDataJpaTest extends AbstractMealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testGetWithUser() throws Exception {
        Map<List<Meal>, User> map = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        User user = map.values().iterator().next();
        List<Meal> meals = map.entrySet().stream().map(entry -> entry.getKey()).findAny().get();
        UserTestData.assertMatch(user, ADMIN);
        MealTestData.assertMatch(meals, ADMIN_MEAL1, ADMIN_MEAL2);
    }
}

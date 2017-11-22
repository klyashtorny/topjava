package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final List<Meal> USER_MEAL = Arrays.asList(
            new Meal(100002, LocalDateTime.of(2017, Month.NOVEMBER, 19, 9, 0), "Завтрак", 500),
            new Meal(100003, LocalDateTime.of(2017, Month.NOVEMBER, 19, 13, 0), "Обед", 1000),
            new Meal(100004, LocalDateTime.of(2017, Month.NOVEMBER, 19, 20, 0), "Ужин", 500)
    );
    public static final List<Meal> ADMIN_MEAL = Arrays.asList(
            new Meal(100005, LocalDateTime.of(2017, Month.NOVEMBER, 20, 9, 0), "Завтрак", 500),
            new Meal(100006, LocalDateTime.of(2017, Month.NOVEMBER, 19, 13, 0), "Обед", 1000),
            new Meal(100007, LocalDateTime.of(2017, Month.NOVEMBER, 19, 13, 0), "Обед", 1000)
    );

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(USER_MEAL);
    }

}

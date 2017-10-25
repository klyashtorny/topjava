package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)

        );

        long t1 = System.currentTimeMillis();
        System.out.println(getFilteredWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        long t5 = System.currentTimeMillis();
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        long t6 = System.currentTimeMillis();
        System.out.println(t6 - t5);

        long t3 = System.currentTimeMillis();
        System.out.println(getFilteredExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3);

    }

    // with cycle method:
    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapDateMeal = new HashMap<>();

        for (UserMeal userMeal : mealList) {
            LocalDate key = userMeal.getDateTime().toLocalDate();
            if (!mapDateMeal.containsKey(key)) mapDateMeal.put(key, 0);
            mapDateMeal.put(key, mapDateMeal.get(key) + userMeal.getCalories());
        }

        List<UserMealWithExceed> listUserExceed = new ArrayList<>();

        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                listUserExceed.add(new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(), userMeal.getCalories(),
                        mapDateMeal.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return listUserExceed;
    }

    // with stream method:
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapDateMeal =
                mealList.stream()
                        .collect(Collectors.groupingBy(mapUser -> mapUser.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(users -> TimeUtil.isBetween(users.getDateTime().toLocalTime(), startTime, endTime))
                .map(users -> new UserMealWithExceed(users.getDateTime(), users.getDescription(),
                        users.getCalories(), mapDateMeal.get(users.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(toList());

    }

    // One return with stream:
    public static List<UserMealWithExceed> getFilteredExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return mealList.stream()
                .filter(users -> TimeUtil.isBetween(users.getDateTime().toLocalTime(), startTime, endTime))
                .map(users -> new UserMealWithExceed(users.getDateTime(), users.getDescription(),
                        users.getCalories(), mealList.stream()
                        .collect(Collectors.groupingBy(mapUser -> mapUser.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories))).get(users.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(toList());
    }

}

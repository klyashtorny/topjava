package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)

        );


        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(15,0), 2000));

    }

    public static List<UserMealWithExceed>  getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapDateMeal = new HashMap<>();

        for (UserMeal userMeal : mealList) {
            LocalDate key = userMeal.getDateTime().toLocalDate();
            if (!mapDateMeal.containsKey(key)) mapDateMeal.put(key, 0);
            mapDateMeal.put(key, mapDateMeal.get(key) + userMeal.getCalories());
        }

        List<UserMealWithExceed> listUserExceed = new ArrayList<>();

        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean exceed = false;
                if (mapDateMeal.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay) exceed = true;
                listUserExceed.add(new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(), userMeal.getCalories(), exceed));
            }
        }
        return listUserExceed;
    }
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay){

        Map<LocalDate, Integer> mapDateMeal =
                mealList.stream()
                        .collect(Collectors.groupingBy(mapUser -> mapUser.getDateTime().toLocalDate(),
                    Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(users -> TimeUtil.isBetween(users.getDateTime().toLocalTime(), startTime, endTime))
                .map(users -> new UserMealWithExceed(users.getDateTime(), users.getDescription(),
                        users.getCalories(), mapDateMeal.get(users.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

    }

}

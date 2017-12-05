package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserRepository{
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default Map<User, List<Meal>> getWithMeal(int id){
        return null;
    }
}
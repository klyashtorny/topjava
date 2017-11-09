package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImplement implements MealDao {

    private final static AtomicInteger count = new AtomicInteger(0);

    private Map<Integer, Meal> mapRepository = new ConcurrentHashMap<>();
    {
        MealsUtil.MEALS.forEach(this::add);
    }

    @Override
    public Meal add(Meal meal) {
        if(meal.isNew()){
            meal.setId(count.incrementAndGet());
        }
        mapRepository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal get(int id) {
       return mapRepository.get(id);
    }

    @Override
    public void delete(int id) {
        mapRepository.remove(id);

    }

    @Override
    public Collection<Meal> getAll() {
        return mapRepository.values();
    }
}

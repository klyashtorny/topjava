package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
            }
            repository.put(meal.getId(), meal);
        });
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        } else if (repository.get(meal.getId()).getUserId() == userId) {
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        else return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return (get(id, userId)==null)&&(repository.remove(id)!=null);
    }

    @Override
    public Meal get(int id, int userId) {
      Meal meal = repository.get(id);
        if(meal.getUserId()==userId) return meal;
        if(meal==null) return null;
        else return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values().stream().filter(user -> user.getUserId()==userId)
                .sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())).collect(Collectors.toList());
    }
}


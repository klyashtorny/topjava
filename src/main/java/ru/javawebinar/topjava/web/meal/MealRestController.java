package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getAllByStartTime");

        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id()),
                startDate == null ? LocalDate.MIN : startDate,
                startTime == null ? LocalTime.MIN : startTime,
                endDate   == null ? LocalDate.MAX : endDate,
                endTime   == null ? LocalTime.MAX : endTime,
                AuthorizedUser.getCaloriesPerDay());
    }

    public boolean delete(int id) throws NotFoundException {
        log.info("delete {}", id);
        return service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) throws NotFoundException {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal, int userId) throws NotFoundException{
        log.info("create {} with userId={}", meal, userId);
       return service.create(meal, userId);
    }

    public void update(Meal meal, int userId) throws NotFoundException{
        log.info("update {} with userId={}", meal, userId);
        service.create(meal, userId);
    }


}
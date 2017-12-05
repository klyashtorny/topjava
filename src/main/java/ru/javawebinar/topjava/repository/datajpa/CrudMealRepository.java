package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    Meal save(Meal meal);

    @Transactional
    @Modifying
    int deleteMealByIdAndUserId(int id, int userId);

    @Override
    Optional<Meal> findById(Integer id);

    List<Meal> findMealByUserIdOrderByIdDesc(int id);

    List<Meal> findMealByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    @Query("SELECT u FROM User u JOIN FETCH u.meals WHERE u.id = :id")
    Meal getOne(Integer integer);
}

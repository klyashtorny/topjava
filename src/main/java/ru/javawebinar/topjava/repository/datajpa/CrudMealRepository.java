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

@Transactional(readOnly = true)
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
    Meal getOne(Integer integer);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2 ORDER BY m.dateTime DESC ")
    Meal getWithUser(Integer id, Integer userId);
}

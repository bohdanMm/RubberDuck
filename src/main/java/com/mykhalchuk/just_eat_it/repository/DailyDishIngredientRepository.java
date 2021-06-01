package com.mykhalchuk.just_eat_it.repository;

import com.mykhalchuk.just_eat_it.domain.entity.DailyDishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyDishIngredientRepository extends JpaRepository<DailyDishIngredient, Long> {

    @Query("select ddi from DailyDishIngredient ddi " +
            "where ddi.dailyDish.id = :dailyDishId")
    List<DailyDishIngredient> findByDailyDishId(Long dailyDishId);
}

package com.mykhalchuk.just_eat_it.repository;

import com.mykhalchuk.just_eat_it.domain.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query(value = "select d.* " +
            "from dish d " +
            "join dish_dish_categories dc on d.id = dc.dish_id " +
            "where dc.dish_categories = :dishCategory ", nativeQuery = true)
    List<Dish> findByCategory(String dishCategory);
}

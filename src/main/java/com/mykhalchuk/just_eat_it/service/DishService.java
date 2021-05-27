package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DishCreationDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishShortInfoDto;
import com.mykhalchuk.just_eat_it.domain.entity.Dish;
import com.mykhalchuk.just_eat_it.domain.entity.DishIngredient;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import com.mykhalchuk.just_eat_it.exception.EntityNotFoundException;
import com.mykhalchuk.just_eat_it.mapper.DishMapper;
import com.mykhalchuk.just_eat_it.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final IngredientService ingredientService;

    public List<DishShortInfoDto> getDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishMapper::toShortDto)
                .collect(Collectors.toList());
    }

    public void create(DishCreationDto dishDto) {
        Dish dish = dishMapper.toEntity(dishDto);
        int calories = dish.getDishIngredients()
                .stream()
                .mapToInt(this::calculateIngredientCalories)
                .sum();
        dish.setCalories(calories);
        dishRepository.save(dish);
    }

    public List<Dish> getShuffledByCategory(String categoryName) {
        List<Dish> dishes = dishRepository.findByCategory(categoryName);
        Collections.shuffle(dishes);
        return dishes;
    }

    public DishDto getById(Long dishId) {
        return dishRepository.findById(dishId)
                .map(dishMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Dish with id " + dishId + " not found"));
    }


    private Integer calculateIngredientCalories(DishIngredient dishIngredient) {
        Ingredient ingredient = ingredientService.findById(dishIngredient.getIngredient().getId());
        return (BigDecimal.valueOf(ingredient.getCalories())
                .multiply(BigDecimal.valueOf(dishIngredient.getAmount()))
                .divide(BigDecimal.valueOf(100), 0, RoundingMode.CEILING))
                .intValue();
    }
}

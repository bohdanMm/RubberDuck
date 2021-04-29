package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DishCreationDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishIngredientDto;
import com.mykhalchuk.just_eat_it.domain.entity.Dish;
import com.mykhalchuk.just_eat_it.domain.entity.DishIngredient;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import com.mykhalchuk.just_eat_it.exception.BadRequestException;
import com.mykhalchuk.just_eat_it.mapper.DishMapper;
import com.mykhalchuk.just_eat_it.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final IngredientService ingredientService;

    public List<DishDto> getDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    public void create(DishCreationDto dishDto) {
        validateDishPercentage(dishDto);
        Dish dish = dishMapper.toEntity(dishDto);
        dishRepository.save(dish);
    }

    private void validateDishPercentage(DishCreationDto dishDto) {
        int percentageOfIngredients = dishDto.getDishIngredients()
                .stream()
                .mapToInt(DishIngredientDto::getAmount)
                .sum();
        if (percentageOfIngredients != 100){
            throw new BadRequestException("Percentage of ingredients should be 100");
        }
    }

//    private Integer calculateIngredientCalories(DishIngredient dishIngredient) {
//        Ingredient ingredient = ingredientService.findById(dishIngredient.getIngredient().getId());
//        return (BigDecimal.valueOf(ingredient.getCalories())
//                .multiply(BigDecimal.valueOf(dishIngredient.getAmount()))
//                .divide(BigDecimal.valueOf(100), 0, RoundingMode.CEILING))
//                .intValue();
//    }


}

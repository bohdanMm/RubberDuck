package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DishIngredientDto;
import com.mykhalchuk.just_eat_it.domain.entity.DailyDishIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DailyDishIngredientMapper {

    @Mapping(target = "ingredientId", source = "ingredient.id")
    @Mapping(target = "ingredientName", source = "ingredient.name")
    DishIngredientDto toDishIngredientDto(DailyDishIngredient dailyDishIngredient);
}

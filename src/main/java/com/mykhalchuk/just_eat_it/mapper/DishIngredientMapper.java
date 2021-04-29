package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DishIngredientDto;
import com.mykhalchuk.just_eat_it.domain.entity.DishIngredient;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = Ingredient.class)
public interface DishIngredientMapper {

    @Mapping(target = "ingredient", expression = "java(new Ingredient(dto.getIngredientId()))")
    DishIngredient toEntity(DishIngredientDto dto);
}

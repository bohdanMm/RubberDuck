package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DishIngredientDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishIngredientRequest;
import com.mykhalchuk.just_eat_it.domain.entity.DishIngredient;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Ingredient.class, AmountType.class})
public interface DishIngredientMapper {

    @Mapping(target = "ingredientId", source = "ingredient.id")
    @Mapping(target = "ingredientName", source = "ingredient.name")
    DishIngredientDto toDto(DishIngredient dishIngredient);

    @Mapping(target = "ingredient", expression = "java(new Ingredient(dto.getIngredientId()))")
    @Mapping(target = "amountType", expression = "java(AmountType.GRAM)")
    DishIngredient toEntity(DishIngredientRequest dto);
}

package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DailyDishDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishDto;
import com.mykhalchuk.just_eat_it.domain.entity.DailyDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DailyDishIngredientMapper.class)
public interface DailyDishMapper {

    @Mapping(target = "dishId", source = "dish.id")
    @Mapping(target = "dishName", source = "dish.name")
    @Mapping(target = "dishPictureUrl", source = "dish.pictureUrl")
    DailyDishDto toDto(DailyDish dailyDish);

    @Mapping(target = "pictureUrl", source = "dish.pictureUrl")
    @Mapping(target = "name", source = "dish.name")
    @Mapping(target = "dishIngredients", source = "dailyDishIngredients")
    DishDto toDishDto(DailyDish dailyDish);
}

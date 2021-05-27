package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DailyDishDto;
import com.mykhalchuk.just_eat_it.domain.entity.DailyDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DailyDishMapper {

    @Mapping(target = "dishId", source = "dish.id")
    @Mapping(target = "dishName", source = "dish.name")
    @Mapping(target = "dishPictureUrl", source = "dish.pictureUrl")
    DailyDishDto toDto(DailyDish dailyDish);
}

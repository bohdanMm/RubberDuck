package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.entity.DishCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishCategoryMapper {

    DishCategory fromId(Long id);
}

package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.IngredientDto;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    Ingredient toEntity(IngredientDto dto);

    IngredientDto toDto(Ingredient dto);
}

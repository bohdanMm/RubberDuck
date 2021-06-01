package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.IngredientDto;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = AmountType.class)
public interface IngredientMapper {

    @Mapping(target = "amountType", expression = "java(AmountType.GRAM)")
    Ingredient toEntity(IngredientDto dto);

    IngredientDto toDto(Ingredient dto);
}

package com.mykhalchuk.just_eat_it.domain.dto.dish;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishIngredientDto {

    private Long ingredientId;

    private Integer amount;

    private String ingredientName;

    private String amountType;

    private Long calories;
}

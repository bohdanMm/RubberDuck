package com.mykhalchuk.just_eat_it.domain.dto.dish;

import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishIngredientDto {

    private Long ingredientId;

    private Integer amount;

    private AmountType amountType;
}

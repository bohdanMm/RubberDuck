package com.mykhalchuk.just_eat_it.domain.dto;

import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer calories;

    private AmountType amountType;
}

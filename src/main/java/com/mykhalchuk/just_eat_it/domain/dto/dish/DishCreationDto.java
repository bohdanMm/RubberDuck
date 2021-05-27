package com.mykhalchuk.just_eat_it.domain.dto.dish;

import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishCreationDto {

    @NotNull
    private String name;

    private String pictureUrl;

    private List<DishIngredientRequest> dishIngredients;

    private List<DailyDishType> dishCategories;
}

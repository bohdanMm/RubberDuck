package com.mykhalchuk.just_eat_it.domain.dto.dish;

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

    private List<DishIngredientDto> dishIngredients;

    private List<Long> dishCategories;
}

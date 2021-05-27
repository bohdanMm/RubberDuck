package com.mykhalchuk.just_eat_it.domain.dto.dish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyDishDto {

    private Long id;

    private String dishId;

    private String dishName;

    private String dishPictureUrl;

    private String type;

    private Integer calories;
}

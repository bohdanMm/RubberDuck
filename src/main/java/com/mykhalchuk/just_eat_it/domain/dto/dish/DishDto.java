package com.mykhalchuk.just_eat_it.domain.dto.dish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishDto {

    private Long id;

    private Integer calories;

    private String name;

    private String pictureUrl;
}

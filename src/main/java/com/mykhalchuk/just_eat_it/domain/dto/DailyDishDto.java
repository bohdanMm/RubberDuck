package com.mykhalchuk.just_eat_it.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyDishDto {

    private Long id;

    private String dishName;

    private String dishPictureUrl;

    private String type;

    private Integer calories;
}

package com.mykhalchuk.just_eat_it.domain.dto;

import com.mykhalchuk.just_eat_it.domain.dto.dish.DailyDishDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DailyMenuDto {

    private Long id;

    private LocalDate menuDate;

    private List<DailyDishDto> dailyDishes;
}

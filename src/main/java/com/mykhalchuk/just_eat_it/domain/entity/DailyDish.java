package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.DailyMenu;
import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class DailyDish extends IdHolder {

    private Long id;

    @ManyToOne
    private DailyMenu dailyMenu;

    @ManyToOne
    private Dish dish;

    @Enumerated(EnumType.STRING)
    private DailyDishType type;

    private Long calories;
}

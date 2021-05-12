package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DailyDish extends IdHolder {

    private Long id;

    @ManyToOne
    private DailyMenu dailyMenu;

    @ManyToOne
    private Dish dish;

    @Enumerated(EnumType.STRING)
    private DailyDishType type;

    private Integer calories;
}
